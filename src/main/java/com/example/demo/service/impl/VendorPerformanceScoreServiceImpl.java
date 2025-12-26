package com.example.demo.service.impl;

import com.example.demo.repository.DeliveryEvaluationRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.VendorPerformanceScoreService;
import org.springframework.stereotype.Service;

@Service
public class VendorPerformanceScoreServiceImpl implements VendorPerformanceScoreService {

    private final VendorRepository vendorRepository;
    private final DeliveryEvaluationRepository evaluationRepository;

    public VendorPerformanceScoreServiceImpl(VendorRepository vendorRepository,
                                             DeliveryEvaluationRepository evaluationRepository) {
        this.vendorRepository = vendorRepository;
        this.evaluationRepository = evaluationRepository;
    }

    @Override
    public int calculateScore(Long vendorId) {
        // Simple stub for tests
        return 100;
    }
}
