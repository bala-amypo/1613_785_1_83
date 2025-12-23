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

    public VendorTier createTier(VendorTier tier) {
        if (tier.getMinScoreThreshold() < 0 || tier.getMinScoreThreshold() > 100)
            throw new IllegalArgumentException("between 0 and 100");

        return repo.save(tier);
    }

    public VendorTier updateTier(Long id, VendorTier tier) {
        VendorTier t = getTierById(id);
        t.setDescription(tier.getDescription());
        t.setMinScoreThreshold(tier.getMinScoreThreshold());
        return repo.save(t);
    }

    public VendorTier getTierById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found"));
    }

    public List<VendorTier> getAllTiers() {
        return repo.findAll();
    }

    public void deactivateTier(Long id) {
        VendorTier t = getTierById(id);
        t.setActive(false);
        repo.save(t);
    }
}
