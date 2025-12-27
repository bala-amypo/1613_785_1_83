package com.example.demo.service.impl;

import com.example.demo.model.VendorPerformanceScore;
import com.example.demo.model.VendorTier;
import com.example.demo.repository.VendorPerformanceScoreRepository;
import com.example.demo.repository.VendorTierRepository;
import com.example.demo.service.VendorTierService;
import org.springframework.stereotype.Service;

@Service
public class VendorTierServiceImpl implements VendorTierService {
    private final VendorTierRepository vendorTierRepository;
    private final VendorPerformanceScoreRepository vendorPerformanceScoreRepository;

    public VendorTierServiceImpl(VendorTierRepository vendorTierRepository) {
        this.vendorTierRepository = vendorTierRepository;
    }

    @Override
    public VendorTier createTier(VendorTier tier) {
        if (tier.getMinOverallScore() < 0 || tier.getMinOverallScore() > 100) {
            throw new IllegalArgumentException("Tier threshold must be between 0–100");
        }
        if (vendorTierRepository.existsByTierName(tier.getTierName())) {
            throw new IllegalArgumentException("Tier name must be unique");
        }
        return vendorTierRepository.save(tier);
    }

    @Override
    public String assignTier(Long vendorId) {
        VendorPerformanceScore latestScore = vendorPerformanceScoreRepository.findLatestByVendorId(vendorId)
            .orElseThrow(() -> new IllegalArgumentException("No performance score found"));
        
        return vendorTierRepository.findByActiveTrueOrderByMinOverallScoreDesc().stream()
            .filter(tier -> latestScore.getOverallScore() >= tier.getMinOverallScore())
            .findFirst()
            .map(VendorTier::getTierName)
            .orElse("BRONZE");
    }

    @Override
    public void deactivateTier(Long id) {
        VendorTier tier = vendorTierRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Tier not found"));
        tier.setActive(false);
        vendorTierRepository.save(tier);
    }
}
