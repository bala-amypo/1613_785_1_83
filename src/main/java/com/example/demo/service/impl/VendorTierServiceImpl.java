package com.example.demo.service.impl;

import com.example.demo.model.VendorTier;
import com.example.demo.repository.VendorTierRepository;
import com.example.demo.service.VendorTierService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorTierServiceImpl implements VendorTierService {

    private final VendorTierRepository tierRepository;

    public VendorTierServiceImpl(VendorTierRepository tierRepository) {
        this.tierRepository = tierRepository;
    }

    @Override
    public VendorTier createTier(VendorTier tier) {
        if (tier.getMinScoreThreshold() < 0 || tier.getMinScoreThreshold() > 100) {
            throw new IllegalArgumentException("Threshold must be between 0–100");
        }
        if (tierRepository.existsByTierName(tier.getTierName())) {
            throw new IllegalArgumentException("Tier name must be unique");
        }
        return tierRepository.save(tier);
    }

    @Override
    public VendorTier getTierById(Long id) {
        return tierRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tier not found"));
    }

    @Override
    public List<VendorTier> getAllTiers() {
        return tierRepository.findAll();
    }

    @Override
    public void deactivateTier(Long id) {
        VendorTier tier = getTierById(id);
        tier.setActive(false);
        tierRepository.save(tier);
    }
}
