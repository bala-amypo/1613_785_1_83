package com.example.demo.service.impl;

import com.example.demo.entity.Vendor;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.VendorService;
import org.springframework.stereotype.Service;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    public Vendor createVendor(Vendor vendor) {
        if (vendorRepository.existsByName(vendor.getName())) {
            throw new IllegalArgumentException("unique");
        }
        return vendorRepository.save(vendor);
    }

    public Vendor getVendorById(Long id) {
        return vendorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found"));
    }

    public void deactivateVendor(Long id) {
        Vendor v = getVendorById(id);
        v.setActive(false);
        vendorRepository.save(v);
    }
}
