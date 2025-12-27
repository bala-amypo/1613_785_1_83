package com.example.demo.service.impl;

import com.example.demo.model.Vendor;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.VendorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public Vendor createVendor(Vendor vendor) {
        if (vendorRepository.existsByName(vendor.getName())) {
            throw new IllegalArgumentException("Vendor name must be unique");
        }
        vendor.setActive(true);
        return vendorRepository.save(vendor);
    }

    @Override
    public Vendor updateVendor(Long id, Vendor update) {
        Vendor existing = vendorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vendor not found"));
        if (update.getName() != null && !update.getName().equals(existing.getName())) {
            if (vendorRepository.existsByName(update.getName())) {
                throw new IllegalArgumentException("Vendor name must be unique");
            }
            existing.setName(update.getName());
        }
        if (update.getContactEmail() != null) existing.setContactEmail(update.getContactEmail());
        if (update.getContactPhone() != null) existing.setContactPhone(update.getContactPhone());

        return vendorRepository.save(existing);
    }

    @Override
    public Vendor getVendorById(Long id) {
        return vendorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vendor not found"));
    }

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    @Override
    public void deactivateVendor(Long id) {
        Vendor existing = vendorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vendor not found"));
        existing.setActive(false);
        vendorRepository.save(existing);
    }
}
