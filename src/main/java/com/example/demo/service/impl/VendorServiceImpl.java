package com.example.demo.service.impl;

import com.example.demo.entity.Vendor;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.VendorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository repo;

    public VendorServiceImpl(VendorRepository repo) {
        this.repo = repo;
    }

    @Override
    public Vendor createVendor(Vendor vendor) {
        if (repo.existsByName(vendor.getName())) {
            throw new IllegalArgumentException("unique");
        }
        return repo.save(vendor);
    }

    @Override
    public Vendor updateVendor(Long id, Vendor vendor) {
        Vendor existing = getVendorById(id);
        existing.setName(vendor.getName());
        existing.setContactEmail(vendor.getContactEmail());
        existing.setContactPhone(vendor.getContactPhone());
        return repo.save(existing);
    }

    @Override
    public Vendor getVendorById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found"));
    }

    @Override
    public List<Vendor> getAllVendors() {
        return repo.findAll();
    }

    @Override
    public void deactivateVendor(Long id) {
        Vendor v = getVendorById(id);
        v.setActive(false);
        repo.save(v);
    }
}
