package com.example.demo.service.impl;

import com.example.demo.model.VendorTier;
import com.example.demo.repository.VendorTierRepository;
import com.example.demo.service.VendorTierService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VendorTierServiceImpl implements VendorTierService {

    private final VendorTierRepository tierRepo;

    public VendorTierServiceImpl(VendorTierRepository tierRepo) {
        this.tierRepo = tierRepo;
    }

    @Override
    public VendorTier createTier(VendorTier tier) {
        if (tierRepo.existsByTierName(tier.getTierName())) {
            throw new IllegalArgumentException("Tier name must be unique");
        }
        if (tier.getMinScoreThreshold() < 0 || tier.getMinScoreThreshold() > 100) {
            throw new IllegalArgumentException("Threshold must be 0–100");
        }
        tier.setActive(true);
        return tierRepo.save(tier);
    }

    @Override
    public VendorTier getTierById(Long id) {
        return tierRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tier not found"));
    }

    @Override
    public List<VendorTier> getAllTiers() {
        return tierRepo.findAll();
    }

    @Override
    public void deactivateTier(Long id) {
        VendorTier tier = tierRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tier not found"));
        tier.setActive(false);
        tierRepo.save(tier);
    }
}
