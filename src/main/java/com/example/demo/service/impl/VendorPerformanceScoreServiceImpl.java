package com.example.demo.service.impl;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.model.Vendor;
import com.example.demo.model.VendorPerformanceScore;
import com.example.demo.model.VendorTier;
import com.example.demo.repository.DeliveryEvaluationRepository;
import com.example.demo.repository.VendorPerformanceScoreRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.repository.VendorTierRepository;
import com.example.demo.service.VendorPerformanceScoreService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VendorPerformanceScoreServiceImpl implements VendorPerformanceScoreService {

    private final VendorPerformanceScoreRepository scoreRepo;
    private final DeliveryEvaluationRepository evalRepo;
    private final VendorRepository vendorRepo;
    private final VendorTierRepository tierRepo;

    public VendorPerformanceScoreServiceImpl(VendorPerformanceScoreRepository scoreRepo,
                                             DeliveryEvaluationRepository evalRepo,
                                             VendorRepository vendorRepo,
                                             VendorTierRepository tierRepo) {
        this.scoreRepo = scoreRepo;
        this.evalRepo = evalRepo;
        this.vendorRepo = vendorRepo;
        this.tierRepo = tierRepo;
    }

    @Override
    public VendorPerformanceScore calculateScore(Long vendorId) {
        Vendor vendor = vendorRepo.findById(vendorId)
                .orElseThrow(() -> new IllegalArgumentException("Vendor not found"));

        List<DeliveryEvaluation> evaluations = evalRepo.findByVendorId(vendorId);

        double total = evaluations.size();
        if (total == 0) total = 1; // avoid division by zero

        long onTimeCount = evaluations.stream().filter(DeliveryEvaluation::getMeetsDeliveryTarget).count();
        long qualityCount = evaluations.stream().filter(DeliveryEvaluation::getMeetsQualityTarget).count();

        double onTimePercentage = (onTimeCount / total) * 100;
        double qualityPercentage = (qualityCount / total) * 100;
        double overallScore = (onTimePercentage + qualityPercentage) / 2;

        VendorPerformanceScore score = new VendorPerformanceScore();
        score.setVendor(vendor);
        score.setOnTimePercentage(onTimePercentage);
        score.setQualityCompliancePercentage(qualityPercentage);
        score.setOverallScore(overallScore);
        score.setCalculatedAt(LocalDate.now());

        // assign tier if any
        List<VendorTier> tiers = tierRepo.findByActiveTrueOrderByMinScoreThresholdDesc();
        for (VendorTier tier : tiers) {
            if (overallScore >= tier.getMinScoreThreshold()) {
                score.setTier(tier);
                break;
            }
        }

        return scoreRepo.save(score);
    }

    @Override
    public VendorPerformanceScore getLatestScore(Long vendorId) {
        List<VendorPerformanceScore> list = scoreRepo.findByVendorOrderByCalculatedAtDesc(vendorId);
        if (list.isEmpty()) return null;
        return list.get(0);
    }

    @Override
    public List<VendorPerformanceScore> getScoresForVendor(Long vendorId) {
        return scoreRepo.findByVendorOrderByCalculatedAtDesc(vendorId);
    }
}
