package com.example.demo.service.impl;

import com.example.demo.entity.VendorTier;
import com.example.demo.repository.VendorTierRepository;
import com.example.demo.service.VendorTierService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorTierServiceImpl implements VendorTierService {

    private final VendorTierRepository repository;

    public VendorTierServiceImpl(VendorTierRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<VendorTier> getAllVendorTiers() {
        return repository.findAll();
    }

    @Override
    public VendorTier getVendorTierById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public VendorTier createVendorTier(VendorTier vendorTier) {
        return repository.save(vendorTier);
    }
}
