package com.example.demo.service.impl;

import com.example.demo.entity.Vendor;
import com.example.demo.repository.VendorRepository;

import org.springframework.stereotype.Service;

@Service
public class VendorServiceImpl {

    private final VendorRepository repo;

    public VendorServiceImpl(VendorRepository repo) {
        this.repo = repo;
    }

    public Vendor createVendor(Vendor v) {
        if (repo.existsByName(v.getName()))
            throw new IllegalArgumentException("unique");
        return repo.save(v);
    }

    public Vendor getVendorById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found"));
    }
}
