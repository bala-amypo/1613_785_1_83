package com.example.demo.service.impl;

import com.example.demo.model.Vendor;
import com.example.demo.model.VendorPerformanceScore;
import com.example.demo.model.VendorTier;
import com.example.demo.repository.DeliveryEvaluationRepository;
import com.example.demo.repository.VendorPerformanceScoreRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.repository.VendorTierRepository;
import com.example.demo.service.VendorPerformanceScoreService;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;

@Service
public class VendorPerformanceScoreServiceImpl implements VendorPerformanceScoreService {
    private final VendorPerformanceScoreRepository vendorPerformanceScoreRepository;
    private final DeliveryEvaluationRepository deliveryEvaluationRepository;
    private final VendorRepository vendorRepository;
    private final VendorTierRepository vendorTierRepository;

    public VendorPerformanceScoreServiceImpl(VendorPerformanceScoreRepository vendorPerformanceScoreRepository,
                                           DeliveryEvaluationRepository deliveryEvaluationRepository,
                                           VendorRepository vendorRepository,
                                           VendorTierRepository vendorTierRepository) {
        this.vendorPerformanceScoreRepository = vendorPerformanceScoreRepository;
        this.deliveryEvaluationRepository = deliveryEvaluationRepository;
        this.vendorRepository = vendorRepository;
        this.vendorTierRepository = vendorTierRepository;
    }

    @Override
    public VendorPerformanceScore calculatePerformanceScore(Long vendorId) {
        return calculateScore(vendorId);
    }

    @Override
    public VendorPerformanceScore calculateScore(Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId)
            .orElseThrow(() -> new IllegalArgumentException("Vendor not found"));
        
        List<com.example.demo.model.DeliveryEvaluation> evaluations = 
            deliveryEvaluationRepository.findByVendorIdOrderByEvaluationDateDesc(vendorId);
        
        if (evaluations.isEmpty()) {
            VendorPerformanceScore score = new VendorPerformanceScore();
            score.setVendorId(vendorId);
            score.setDeliveryComplianceRate(0.0);
            score.setQualityComplianceRate(0.0);
            score.setOverallScore(0.0);
            return vendorPerformanceScoreRepository.save(score);
        }
        
        long deliveryPassCount = evaluations.stream()
            .filter(e -> Boolean.TRUE.equals(e.getMeetsDeliveryTarget()))
            .count();
        long qualityPassCount = evaluations.stream()
            .filter(e -> Boolean.TRUE.equals(e.getMeetsQualityTarget()))
            .count();
        
        double deliveryRate = (deliveryPassCount * 100.0) / evaluations.size();
        double qualityRate = (qualityPassCount * 100.0) / evaluations.size();
        double overall = (deliveryRate + qualityRate) / 2;
        
        VendorPerformanceScore score = new VendorPerformanceScore();
        score.setVendorId(vendorId);
        score.setDeliveryComplianceRate(deliveryRate);
        score.setQualityComplianceRate(qualityRate);
        score.setOverallScore(overall);
        
        return vendorPerformanceScoreRepository.save(score);
    }

    @Override
    public VendorPerformanceScore getLatestScore(Long vendorId) {
        return vendorPerformanceScoreRepository.findByVendorOrderByCalculatedAtDesc(vendorId).stream()
            .findFirst()
            .orElse(null);
    }

    @Override
    public List<VendorPerformanceScore> getScoresForVendor(Long vendorId) {
        return vendorPerformanceScoreRepository.findByVendorOrderByCalculatedAtDesc(vendorId);
    }
}
