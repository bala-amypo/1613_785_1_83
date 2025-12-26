package com.example.demo.service.impl;

import com.example.demo.model.VendorTier;
import com.example.demo.repository.VendorTierRepository;
import com.example.demo.service.VendorTierService;

public class VendorTierServiceImpl implements VendorTierService {

    private final VendorTierRepository repo;

    public VendorTierServiceImpl(VendorTierRepository repo) {
        this.repo = repo;
    }

    @Override
    public VendorTier createTier(VendorTier tier) {

        if (tier.getMinScoreThreshold() < 0 || tier.getMinScoreThreshold() > 100) {
            throw new IllegalArgumentException("Threshold must be between 0–100");
        }

        if (repo.existsByTierName(tier.getTierName())) {
            throw new IllegalArgumentException("Tier name must be unique");
        }

        return repo.save(tier);
    }

    @Override
    public void deactivateTier(Long id) {
        VendorTier tier = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tier not found"));
        tier.setActive(false);
        repo.save(tier);
    }
}
