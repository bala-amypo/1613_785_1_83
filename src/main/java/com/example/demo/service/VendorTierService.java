package com.example.demo.service.impl;

import com.example.demo.entity.VendorTier;
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
            throw new IllegalArgumentException("unique");
        }
        if(tier.getMinScoreThreshold() < 0 || tier.getMinScoreThreshold() > 100)
            throw new IllegalArgumentException("Min score 0-100");
        return tierRepo.save(tier);
    }

    @Override
    public VendorTier getTierById(Long id) {
        return tierRepo.findById(id).orElseThrow(() -> new IllegalStateException("not found"));
    }

    @Override
    public List<VendorTier> getAllTiers() {
        return tierRepo.findByActiveTrueOrderByMinScoreThresholdDesc();
    }

    @Override
    public VendorTier updateTier(Long id, VendorTier tier) {
        VendorTier existing = getTierById(id);
        existing.setTierName(tier.getTierName());
        existing.setDescription(tier.getDescription());
        existing.setMinScoreThreshold(tier.getMinScoreThreshold());
        return tierRepo.save(existing);
    }

    @Override
    public VendorTier deactivateTier(Long id) {
        VendorTier t = getTierById(id);
        t.setActive(false);
        return tierRepo.save(t);
    }
}
