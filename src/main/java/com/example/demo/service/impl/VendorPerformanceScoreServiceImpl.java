package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.VendorPerformanceScoreService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class VendorPerformanceScoreServiceImpl
        implements VendorPerformanceScoreService {

    private final VendorPerformanceScoreRepository scoreRepo;
    private final DeliveryEvaluationRepository evalRepo;
    private final VendorRepository vendorRepo;
    private final VendorTierRepository tierRepo;

    public VendorPerformanceScoreServiceImpl(
            VendorPerformanceScoreRepository scoreRepo,
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

        List<DeliveryEvaluation> evaluations =
                evalRepo.findByVendorId(vendorId);

        long total = evaluations.size();
        long onTime = evaluations.stream()
                .filter(DeliveryEvaluation::getMeetsDeliveryTarget)
                .count();

        long quality = evaluations.stream()
                .filter(DeliveryEvaluation::getMeetsQualityTarget)
                .count();

        double onTimePct = total == 0 ? 0 : (onTime * 100.0 / total);
        double qualityPct = total == 0 ? 0 : (quality * 100.0 / total);
        double overall = (onTimePct + qualityPct) / 2;

        VendorPerformanceScore score = new VendorPerformanceScore();
        score.setVendor(vendor);
        score.setScore(overall);
        score.setCalculatedAt(new Timestamp(System.currentTimeMillis()));

        return scoreRepo.save(score);
    }

    @Override
    public VendorPerformanceScore getLatestScore(Long vendorId) {
        return scoreRepo.findByVendorOrderByCalculatedAtDesc(vendorId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Score not found"));
    }

    @Override
    public List<VendorPerformanceScore> getScoresForVendor(Long vendorId) {
        return scoreRepo.findByVendorOrderByCalculatedAtDesc(vendorId);
    }
}
