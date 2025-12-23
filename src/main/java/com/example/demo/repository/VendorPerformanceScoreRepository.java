package com.example.demo.repository;

import com.example.demo.entity.VendorPerformanceScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VendorPerformanceScoreRepository
        extends JpaRepository<VendorPerformanceScore, Long> {

    Optional<VendorPerformanceScore>
    findTopByVendorIdOrderByCalculatedAtDesc(Long vendorId);

    List<VendorPerformanceScore>
    findByVendorIdOrderByCalculatedAtDesc(Long vendorId);
}
