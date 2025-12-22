package com.example.demo.service.impl;

import com.example.demo.entity.VendorPerformanceScore;
import com.example.demo.repository.VendorPerformanceScoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorPerformanceScoreServiceImpl {

    private final VendorPerformanceScoreRepository repository;

    public VendorPerformanceScoreServiceImpl(VendorPerformanceScoreRepository repository) {
        this.repository = repository;
    }

    public List<VendorPerformanceScore> getScoresByVendor(Long vendorId) {
        return repository.findByVendorOrderByCreatedAtDesc(vendorId);
    }
}
