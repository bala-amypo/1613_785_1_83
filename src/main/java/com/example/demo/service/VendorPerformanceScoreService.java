package com.example.demo.service;

import com.example.demo.entity.VendorPerformanceScore;

import java.util.List;

public interface VendorPerformanceScoreService {

    VendorPerformanceScore calculateScore(Long vendorId, double scoreValue);

    VendorPerformanceScore getLatestScore(Long vendorId);

    List<VendorPerformanceScore> getScoresForVendor(Long vendorId);
}
