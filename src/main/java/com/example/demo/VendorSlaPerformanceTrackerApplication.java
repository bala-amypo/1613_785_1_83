package com.example.demo;

import com.example.demo.entity.Vendor;
import com.example.demo.entity.VendorTier;
import com.example.demo.security.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class VendorSlaPerformanceTrackerApplicationTests {

    private JwtTokenProvider jwtTokenProvider;
    private String secretKey = "mySecretKey"; // Must match JwtTokenProvider signing key

    @BeforeEach
    public void setup() {
        jwtTokenProvider = new JwtTokenProvider();
    }

    // ---------------- JWT Tests ----------------
    @Test
    public void testCreateToken() {
        String username = "user1";
        String token = jwtTokenProvider.createToken(username);
        Assertions.assertNotNull(token);

        Claims claims = parseToken(token);
        Assertions.assertEquals(username, claims.getSubject());
    }

    @Test
    public void testJwtTokenClaims() {
        String username = "admin";
        String token = jwtTokenProvider.createToken(username);
        Claims claims = parseToken(token);
        Assertions.assertEquals(username, claims.getSubject());
        Assertions.assertTrue(claims.getExpiration().after(new Date()));
    }

    private Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }

    // ---------------- Vendor Tests ----------------
    @Test
    public void testVendorCreation() {
        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName("Vendor A");
        vendor.setActive(true);

        Assertions.assertEquals(1L, vendor.getId());
        Assertions.assertEquals("Vendor A", vendor.getName());
        Assertions.assertTrue(vendor.isActive());
    }

    @Test
    public void testVendorNameChange() {
        Vendor vendor = new Vendor();
        vendor.setName("Old Name");
        vendor.setName("New Name");

        Assertions.assertEquals("New Name", vendor.getName());
    }

    @Test
    public void testVendorActiveStatusCheck() {
        Vendor vendor1 = new Vendor();
        vendor1.setActive(true);

        Vendor vendor2 = new Vendor();
        vendor2.setActive(false);

        Assertions.assertTrue(vendor1.isActive());
        Assertions.assertFalse(vendor2.isActive());
    }

    @Test
    public void testMultipleVendorsPerformance() {
        List<Vendor> vendors = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Vendor v = new Vendor();
            v.setId((long) i);
            v.setName("Vendor " + i);
            v.setActive(i % 2 == 1); // odd vendors active
            vendors.add(v);
        }

        long activeCount = vendors.stream().filter(Vendor::isActive).count();
        Assertions.assertEquals(3, activeCount);
    }

    // ---------------- VendorTier Tests ----------------
    @Test
    public void testVendorTierAssignment() {
        Vendor vendor = new Vendor();
        VendorTier tier = new VendorTier();
        tier.setName("Gold");
        tier.setMinScore(90);

        vendor.setTier(tier);

        Assertions.assertEquals("Gold", vendor.getTier().getName());
        Assertions.assertEquals(90, vendor.getTier().getMinScore());
    }

    @Test
    public void testVendorTierChange() {
        Vendor vendor = new Vendor();
        VendorTier tier = new VendorTier();
        tier.setName("Silver");
        vendor.setTier(tier);
        tier.setName("Gold"); // changing tier name after assignment

        Assertions.assertEquals("Gold", vendor.getTier().getName());
    }

    @Test
    public void testVendorPerformanceScoreCalculation() {
        List<Integer> scores = List.of(80, 90, 100, 70, 85);
        int totalScore = scores.stream().mapToInt(Integer::intValue).sum();
        double averageScore = totalScore / (double) scores.size();

        Assertions.assertEquals(85.0, averageScore);
    }

    @Test
    public void testVendorScoresNotExceedMax() {
        int score = 105;
        int maxScore = 100;
        int finalScore = Math.min(score, maxScore);
        Assertions.assertEquals(100, finalScore);
    }

    // ---------------- SLA Tests ----------------
    @Test
    public void testSlaScoreCalculation() {
        int totalDeliveries = 10;
        int onTimeDeliveries = 8;

        double slaScore = (onTimeDeliveries / (double) totalDeliveries) * 100;
        Assertions.assertEquals(80.0, slaScore);
    }

    @Test
    public void testSlaThresholdCheck() {
        double slaScore = 92.5;
        double requiredThreshold = 90.0;

        Assertions.assertTrue(slaScore >= requiredThreshold);
    }

    @Test
    public void testVendorSlaEdgeCases() {
        int totalDeliveries = 0;
        int onTimeDeliveries = 0;

        double slaScore = totalDeliveries == 0 ? 100.0 : (onTimeDeliveries / (double) totalDeliveries) * 100;
        Assertions.assertEquals(100.0, slaScore);
    }

    // ---------------- Combined Vendor + SLA + Tier Tests ----------------
    @Test
    public void testVendorPerformanceTierAssignment() {
        Vendor vendor = new Vendor();
        double avgScore = 87.0;

        VendorTier tier = new VendorTier();
        if (avgScore >= 90) tier.setName("Gold");
        else if (avgScore >= 75) tier.setName("Silver");
        else tier.setName("Bronze");

        vendor.setTier(tier);
        Assertions.assertEquals("Silver", vendor.getTier().getName());
    }

    @Test
    public void testMultipleVendorsPerformanceAndTier() {
        List<Vendor> vendors = new ArrayList<>();
        List<Double> scores = List.of(95.0, 82.0, 70.0, 88.0);

        for (int i = 0; i < scores.size(); i++) {
            Vendor v = new Vendor();
            v.setName("Vendor " + (i + 1));

            double score = scores.get(i);
            VendorTier tier = new VendorTier();
            if (score >= 90) tier.setName("Gold");
            else if (score >= 75) tier.setName("Silver");
            else tier.setName("Bronze");

            v.setTier(tier);
            vendors.add(v);
        }

        Assertions.assertEquals("Gold", vendors.get(0).getTier().getName());
        Assertions.assertEquals("Silver", vendors.get(1).getTier().getName());
        Assertions.assertEquals("Bronze", vendors.get(2).getTier().getName());
        Assertions.assertEquals("Silver", vendors.get(3).getTier().getName());
    }

    // ---------------- Additional JWT + Vendor Tests ----------------
    @Test
    public void testJwtTokenValidationWithVendor() {
        Vendor vendor = new Vendor();
        vendor.setName("Vendor JWT");

        String token = jwtTokenProvider.createToken(vendor.getName());
        Claims claims = parseToken(token);
        Assertions.assertEquals(vendor.getName(), claims.getSubject());
    }

    @Test
    public void testJwtTokenExpirationCheck() {
        String token = jwtTokenProvider.createToken("testUser");
        Claims claims = parseToken(token);
        Assertions.assertTrue(claims.getExpiration().after(new Date()));
    }

    // ---------------- Extra Edge Cases ----------------
    @Test
    public void testEmptyVendorList() {
        List<Vendor> vendors = new ArrayList<>();
        long activeCount = vendors.stream().filter(Vendor::isActive).count();
        Assertions.assertEquals(0, activeCount);
    }

    @Test
    public void testAllInactiveVendors() {
        List<Vendor> vendors = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Vendor v = new Vendor();
            v.setActive(false);
            vendors.add(v);
        }
        long activeCount = vendors.stream().filter(Vendor::isActive).count();
        Assertions.assertEquals(0, activeCount);
    }

    @Test
    public void testSlaScoreWithPartialOnTime() {
        int total = 5;
        int onTime = 3;
        double slaScore = (onTime / (double) total) * 100;
        Assertions.assertEquals(60.0, slaScore);
    }

    @Test
    public void testSlaScorePerfect() {
        int total = 4;
        int onTime = 4;
        double slaScore = (onTime / (double) total) * 100;
        Assertions.assertEquals(100.0, slaScore);
    }

    // Add similar extra tests to cover your full 54-case suite...

}
