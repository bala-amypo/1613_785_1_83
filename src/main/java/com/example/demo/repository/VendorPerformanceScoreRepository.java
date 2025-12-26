package com.example.demo.repository;

import com.example.demo.model.VendorPerformanceScore;

import java.util.List;

public interface VendorPerformanceScoreRepository {

    VendorPerformanceScore save(VendorPerformanceScore score);

    List<VendorPerformanceScore> findByVendorOrderByCalculatedAtDesc(Long vendorId);
}
