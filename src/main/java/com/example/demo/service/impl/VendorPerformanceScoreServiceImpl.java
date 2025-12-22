package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.VendorPerformanceScoreService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VendorPerformanceScoreServiceImpl
        implements VendorPerformanceScoreService {

    private final VendorPerformanceScoreRepository scoreRepository;
    private final DeliveryEvaluationRepository evaluationRepository;
    private final VendorRepository vendorRepository;
    private final VendorTierRepository tierRepository;

    public VendorPerformanceScoreServiceImpl(
            VendorPerformanceScoreRepository scoreRepository,
            DeliveryEvaluationRepository evaluationRepository,
            VendorRepository vendorRepository,
            VendorTierRepository tierRepository) {

        this.scoreRepository = scoreRepository;
        this.evaluationRepository = evaluationRepository;
        this.vendorRepository = vendorRepository;
        this.tierRepository = tierRepository;
    }

    public VendorPerformanceScore calculateScore(Long vendorId) {

        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new IllegalArgumentException("not found"));

        List<DeliveryEvaluation> evaluations =
                evaluationRepository.findByVendorId(vendorId);

        long onTime = evaluations.stream()
                .filter(DeliveryEvaluation::getMeetsDeliveryTarget)
                .count();

        long quality = evaluations.stream()
                .filter(DeliveryEvaluation::getMeetsQualityTarget)
                .count();

        double onTimePct = evaluations.isEmpty() ? 0 : (onTime * 100.0 / evaluations.size());
        double qualityPct = evaluations.isEmpty() ? 0 : (quality * 100.0 / evaluations.size());

        VendorPerformanceScore score = new VendorPerformanceScore();
        score.setVendor(vendor);
        score.setOnTimePercentage(onTimePct);
        score.setQualityCompliancePercentage(qualityPct);
        score.setOverallScore((onTimePct + qualityPct) / 2);

        return scoreRepository.save(score);
    }
}
