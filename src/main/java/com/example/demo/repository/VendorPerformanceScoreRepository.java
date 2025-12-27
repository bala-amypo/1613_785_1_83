package com.example.demo.repository;

import com.example.demo.model.VendorPerformanceScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface VendorPerformanceScoreRepository extends JpaRepository<VendorPerformanceScore, Long> {
    @Query("SELECT s FROM VendorPerformanceScore s WHERE s.vendorId = :vendorId ORDER BY s.calculatedAt DESC")
    List<VendorPerformanceScore> findByVendorOrderByCalculatedAtDesc(@Param("vendorId") Long vendorId);
    
    Optional<VendorPerformanceScore> findLatestByVendorId(Long vendorId);
}
