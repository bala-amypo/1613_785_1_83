package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.VendorPerformanceScoreService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
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
        vendorRepository.findById(vendorId); // Test vendor exists
        
        List<DeliveryEvaluation> evaluations = deliveryEvaluationRepository.findByVendorId(vendorId);
        
        VendorPerformanceScore score = new VendorPerformanceScore();
        score.setVendorId(vendorId);
        
        if (evaluations.isEmpty()) {
            score.setDeliveryComplianceRate(0.0);
            score.setQualityComplianceRate(0.0);
            score.setOverallScore(0.0);
        } else {
            long deliveryPass = evaluations.stream().filter(e -> Boolean.TRUE.equals(e.getMeetsDeliveryTarget())).count();
            long qualityPass = evaluations.stream().filter(e -> Boolean.TRUE.equals(e.getMeetsQualityTarget())).count();
            
            score.setDeliveryComplianceRate((deliveryPass * 100.0) / evaluations.size());
            score.setQualityComplianceRate((qualityPass * 100.0) / evaluations.size());
            score.setOverallScore((score.getDeliveryComplianceRate() + score.getQualityComplianceRate()) / 2);
        }
        
        score.setCalculatedAt(LocalDateTime.now());
        return vendorPerformanceScoreRepository.save(score);
    }

    @Override
    public VendorPerformanceScore getLatestScore(Long vendorId) {
        List<VendorPerformanceScore> scores = vendorPerformanceScoreRepository.findByVendorOrderByCalculatedAtDesc(vendorId);
        return scores.isEmpty() ? null : scores.get(0);
    }

    @Override
    public List<VendorPerformanceScore> getScoresForVendor(Long vendorId) {
        return vendorPerformanceScoreRepository.findByVendorOrderByCalculatedAtDesc(vendorId);
    }
}
