package com.example.demo.service.impl;

import com.example.demo.entity.DeliveryEvaluation;
import com.example.demo.entity.Vendor;
import com.example.demo.entity.VendorPerformanceScore;
import com.example.demo.entity.VendorTier;
import com.example.demo.repository.DeliveryEvaluationRepository;
import com.example.demo.repository.VendorPerformanceScoreRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.repository.VendorTierRepository;
import com.example.demo.service.VendorPerformanceScoreService;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
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
        Vendor vendor = vendorRepo.findById(vendorId).orElseThrow(() -> new IllegalStateException("not found"));

        List<DeliveryEvaluation> evaluations = evalRepo.findByVendorId(vendorId);
        if (evaluations.isEmpty()) throw new IllegalStateException("no evaluations");

        long onTime = evaluations.stream().filter(DeliveryEvaluation::getMeetsDeliveryTarget).count();
        long highQuality = evaluations.stream().filter(DeliveryEvaluation::getMeetsQualityTarget).count();
        int total = evaluations.size();

        double onTimePct = (onTime * 100.0) / total;
        double qualityPct = (highQuality * 100.0) / total;

        double overallScore = 0.7 * onTimePct + 0.3 * qualityPct; // example weights

        VendorPerformanceScore score = new VendorPerformanceScore();
        score.setVendor(vendor);
        score.setOnTimePercentage(onTimePct);
        score.setQualityCompliancePercentage(qualityPct);
        score.setOverallScore(overallScore);
        score.setCalculatedAt(new Timestamp(System.currentTimeMillis()));

        return scoreRepo.save(score);
    }

    @Override
    public VendorPerformanceScore getLatestScore(Long vendorId) {
        return scoreRepo.findByVendorOrderByCalculatedAtDesc(vendorId).stream().findFirst()
                .orElseThrow(() -> new IllegalStateException("not found"));
    }

    @Override
    public List<VendorPerformanceScore> getScoresForVendor(Long vendorId) {
        return scoreRepo.findByVendorOrderByCalculatedAtDesc(vendorId);
    }
}
