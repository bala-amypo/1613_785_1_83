package com.example.demo.service.impl;

import com.example.demo.entity.Vendor;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.VendorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepo;

    public VendorServiceImpl(VendorRepository vendorRepo) {
        this.vendorRepo = vendorRepo;
    }

    @Override
    public Vendor createVendor(Vendor vendor) {
        return vendorRepo.save(vendor);
    }

    @Override
    public Vendor updateVendor(Long id, Vendor vendor) {
        Vendor existing = getVendorById(id);
        existing.setName(vendor.getName());
        existing.setContactEmail(vendor.getContactEmail());
        existing.setContactPhone(vendor.getContactPhone());
        return vendorRepo.save(existing);
    }

    @Override
    public Vendor getVendorById(Long id) {
        return vendorRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vendor not found"));
    }

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepo.findAll();
    }

    @Override
    public Vendor deactivateVendor(Long id) {
        Vendor v = getVendorById(id);
        v.setActive(false);
        return vendorRepo.save(v);
    }
}
