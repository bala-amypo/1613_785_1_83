package com.example.demo.service.impl;

import com.example.demo.entity.VendorPerformanceScore;
import com.example.demo.repository.DeliveryEvaluationRepository;
import com.example.demo.repository.VendorPerformanceScoreRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.repository.VendorTierRepository;
import com.example.demo.service.VendorPerformanceScoreService;
import org.springframework.stereotype.Service;

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
        var vendor = vendorRepo.findById(vendorId)
                .orElseThrow(() -> new IllegalArgumentException("not found"));

        var evaluations = evalRepo.findByVendorId(vendorId);

        long onTime = evaluations.stream()
                .filter(e -> Boolean.TRUE.equals(e.getMeetsDeliveryTarget()))
                .count();

        long quality = evaluations.stream()
                .filter(e -> Boolean.TRUE.equals(e.getMeetsQualityTarget()))
                .count();

        double onTimePct = evaluations.isEmpty() ? 0 : onTime * 100.0 / evaluations.size();
        double qualityPct = evaluations.isEmpty() ? 0 : quality * 100.0 / evaluations.size();

        VendorPerformanceScore score = new VendorPerformanceScore();
        score.setVendor(vendor);
        score.setOnTimePercentage(onTimePct);
        score.setQualityCompliancePercentage(qualityPct);
        score.setOverallScore((onTimePct + qualityPct) / 2);

        return scoreRepo.save(score);
    }

    @Override
    public VendorPerformanceScore getLatestScore(Long vendorId) {
        List<VendorPerformanceScore> list =
                scoreRepo.findByVendorOrderByCalculatedAtDesc(vendorId);

        if (list.isEmpty()) {
            throw new IllegalArgumentException("not found");
        }
        return list.get(0);
    }

    @Override
    public List<VendorPerformanceScore> getScoresForVendor(Long vendorId) {
        return scoreRepo.findByVendorOrderByCalculatedAtDesc(vendorId);
    }
}
