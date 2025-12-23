package com.example.demo.service;

import com.example.demo.entity.Vendor;
import com.example.demo.repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {
    private final VendorRepository vendorRepo;

    public VendorService(VendorRepository vendorRepo) {
        this.vendorRepo = vendorRepo;
    }

    public Vendor createVendor(Vendor vendor) {
        if(vendorRepo.existsByName(vendor.getName())) {
            throw new IllegalArgumentException("Vendor name must be unique");
        }
        return vendorRepo.save(vendor);
    }

    public Vendor getVendorById(Long id) {
        return vendorRepo.findById(id)
                .orElseThrow(() -> new IllegalStateException("Vendor not found"));
    }

    public List<Vendor> getAllVendors() {
        return vendorRepo.findAll();
    }

    public Vendor deactivateVendor(Long id) {
        Vendor v = getVendorById(id);
        v.setActive(false);
        return vendorRepo.save(v);
    }
}
