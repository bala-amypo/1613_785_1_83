package com.example.demo.service.impl;

import com.example.demo.model.Vendor;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.VendorService;

import java.util.List;

public class VendorServiceImpl implements VendorService {

    private final VendorRepository repo;

    public VendorServiceImpl(VendorRepository repo) {
        this.repo = repo;
    }

    public Vendor createVendor(Vendor v) {
        if (repo.existsByName(v.getName()))
            throw new IllegalArgumentException("Vendor name must be unique");
        return repo.save(v);
    }

    public Vendor updateVendor(Long id, Vendor update) {
        Vendor existing = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vendor not found"));
        if (update.getContactEmail() != null)
            existing.setContactEmail(update.getContactEmail());
        if (update.getContactPhone() != null)
            existing.setContactPhone(update.getContactPhone());
        return repo.save(existing);
    }

    public Vendor getVendorById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vendor not found"));
    }

    public List<Vendor> getAllVendors() {
        return repo.findAll();
    }

    public void deactivateVendor(Long id) {
        Vendor v = getVendorById(id);
        v.setActive(false);
        repo.save(v);
    }
}
