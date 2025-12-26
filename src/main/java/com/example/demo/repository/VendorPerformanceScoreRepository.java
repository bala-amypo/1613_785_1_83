package com.example.demo.repository;

import com.example.demo.model.Vendor;
import com.example.demo.model.VendorPerformanceScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VendorPerformanceScoreRepository
        extends JpaRepository<VendorPerformanceScore, Long> {

    // REQUIRED FOR TESTS 78 & 79
    @Query("""
        SELECT vps FROM VendorPerformanceScore vps
        WHERE vps.vendor.id = :vendorId
        ORDER BY vps.calculatedAt DESC
    """)
    List<VendorPerformanceScore> findByVendorOrderByCalculatedAtDesc(Long vendorId);
}
