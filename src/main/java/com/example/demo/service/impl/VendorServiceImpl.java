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
        return repo.save(vendor);
    }

    @Override
    public Vendor updateVendor(Long id, Vendor vendor) {
        Vendor v = getVendorById(id);
        v.setName(vendor.getName());
        v.setContactEmail(vendor.getContactEmail());
        v.setContactPhone(vendor.getContactPhone());
        return repo.save(v);
    }

    @Override
    public Vendor getVendorById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vendor not found"));
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
