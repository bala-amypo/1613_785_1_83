package com.example.demo.service.impl;

import com.example.demo.entity.VendorTier;
import com.example.demo.repository.VendorTierRepository;
import com.example.demo.service.VendorTierService;
import java.util.List;

public class VendorTierServiceImpl implements VendorTierService {

    private final VendorTierRepository repository;

    public VendorTierServiceImpl(VendorTierRepository repository) {
        this.repository = repository;
    }

    @Override
    public VendorTier createTier(VendorTier tier) {
        return repository.save(tier);
    }

    @Override
    public VendorTier updateTier(Long id, VendorTier tier) {
        // update logic
        return tier;
    }

    @Override
    public VendorTier getTierById(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalStateException("VendorTier not found"));
    }

    @Override
    public List<VendorTier> getAllTiers() {
        return repository.findAll();
    }

    @Override
    public void deactivateTier(Long id) {
        VendorTier tier = getTierById(id);
        tier.setActive(false);
        repository.save(tier);
    }
}
