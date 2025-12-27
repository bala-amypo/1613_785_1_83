package com.example.demo.service.impl;

import com.example.demo.model.Vendor;
import com.example.demo.repository.VendorPerformanceScoreRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.VendorTierService;
import org.springframework.stereotype.Service;

@Service
public class VendorTierServiceImpl implements VendorTierService {
    private final VendorRepository vendorRepository;
    private final VendorPerformanceScoreRepository vendorPerformanceScoreRepository;

    // ✅ FIXED: Constructor injection for BOTH repositories
    public VendorTierServiceImpl(VendorRepository vendorRepository, 
                                VendorPerformanceScoreRepository vendorPerformanceScoreRepository) {
        this.vendorRepository = vendorRepository;
        this.vendorPerformanceScoreRepository = vendorPerformanceScoreRepository;
    }

    @Override
    public String calculateTier(Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId)
            .orElseThrow(() -> new IllegalArgumentException("Vendor not found"));
        
        double averageScore = vendorPerformanceScoreRepository.findAverageScoreByVendorId(vendorId)
            .orElse(0.0);
        
        if (averageScore >= 90) return "PLATINUM";
        if (averageScore >= 80) return "GOLD";
        if (averageScore >= 70) return "SILVER";
        return "BRONZE";
    }

    @Override
    public String getCurrentTier(Long vendorId) {
        return calculateTier(vendorId);
    }
}
