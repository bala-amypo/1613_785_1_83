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

        if (repo.existsByTierName(tier.getTierName())) {
            throw new IllegalArgumentException("unique");
        }

        if (tier.getMinScoreThreshold() < 0 || tier.getMinScoreThreshold() > 100) {
            throw new IllegalArgumentException("between 0 and 100");
        }

        return repo.save(tier);
    }

    @Override
    public List<VendorTier> getAll() {
        return repo.findAll();
    }

    @Override
    public VendorTier getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found"));
    }
}
