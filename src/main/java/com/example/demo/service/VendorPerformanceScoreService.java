package com.example.demo.service;

import com.example.demo.model.VendorPerformanceScore;

public interface VendorPerformanceScoreService {
    VendorPerformanceScore calculateScore(Long vendorId);
    VendorPerformanceScore getLatestScore(Long vendorId);
    java.util.List<VendorPerformanceScore> getScoresForVendor(Long vendorId);
}
