package com.example.demo.service.impl;

import com.example.demo.entity.VendorTier;
import com.example.demo.repository.VendorTierRepository;
import com.example.demo.service.VendorTierService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorTierServiceImpl implements VendorTierService {

    private final VendorTierRepository repo;

    public VendorTierServiceImpl(VendorTierRepository repo) {
        this.repo = repo;
    }

    @Override
    public VendorTier createTier(VendorTier tier) {
        return repo.save(tier);
    }

    @Override
    public VendorTier updateTier(Long id, VendorTier tier) {
        VendorTier t = getTierById(id);
        t.setTierName(tier.getTierName());
        t.setMinScoreThreshold(tier.getMinScoreThreshold());
        t.setDescription(tier.getDescription());
        return repo.save(t);
    }

    @Override
    public VendorTier getTierById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tier not found"));
    }

    @Override
    public List<VendorTier> getAllTiers() {
        return repo.findAll();
    }

    @Override
    public void deactivateTier(Long id) {
        VendorTier t = getTierById(id);
        t.setActive(false);
        repo.save(t);
    }
}
