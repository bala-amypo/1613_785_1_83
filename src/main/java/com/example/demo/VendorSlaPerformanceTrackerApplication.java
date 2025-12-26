package com.example.demo;

import com.example.demo.security.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VendorSlaPerformanceTrackerApplicationTests {

    private JwtTokenProvider jwtTokenProvider;
    private String secretKey = "mySecretKey"; // Must match JwtTokenProvider's signing key

    @BeforeEach
    public void setup() {
        // Initialize JwtTokenProvider using no-arg constructor
        jwtTokenProvider = new JwtTokenProvider();
    }

    @Test
    public void contextLoads() {
        // Basic context load test
        Assertions.assertNotNull(jwtTokenProvider);
    }

    @Test
    public void testCreateTokenWithUsername1() {
        String username = "user1";
        String token = jwtTokenProvider.createToken(username);
        Assertions.assertNotNull(token);
        Assertions.assertFalse(token.isEmpty());

        Claims claims = parseToken(token);
        Assertions.assertEquals(username, claims.getSubject());
    }

    @Test
    public void testCreateTokenWithUsername2() {
        String username = "user2";
        String token = jwtTokenProvider.createToken(username);
        Assertions.assertNotNull(token);

        Claims claims = parseToken(token);
        Assertions.assertEquals(username, claims.getSubject());
        Assertions.assertTrue(claims.getExpiration().after(new java.util.Date()));
    }

    @Test
    public void testTokenValidation() {
        String username = "testUser";
        String token = jwtTokenProvider.createToken(username);

        // Parse the token manually
        Claims claims = parseToken(token);
        Assertions.assertEquals(username, claims.getSubject());
    }

    @Test
    public void testMultipleTokens() {
        String userA = "alice";
        String userB = "bob";

        String tokenA = jwtTokenProvider.createToken(userA);
        String tokenB = jwtTokenProvider.createToken(userB);

        Claims claimsA = parseToken(tokenA);
        Claims claimsB = parseToken(tokenB);

        Assertions.assertEquals(userA, claimsA.getSubject());
        Assertions.assertEquals(userB, claimsB.getSubject());
        Assertions.assertNotEquals(claimsA.getId(), claimsB.getId());
    }

    // Helper method to parse token
    private Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey.getBytes()) // Make sure this matches your provider
                .parseClaimsJws(token)
                .getBody();
    }

    // Add more tests for your Vendor SLA and performance logic here
    // Example:
    @Test
    public void testDummyVendorLogic() {
        int totalVendors = 5;
        int activeVendors = 4;

        Assertions.assertTrue(activeVendors <= totalVendors);
    }
}
