package com.example.demo.service.impl;

import com.example.demo.entity.DeliveryEvaluation;
import com.example.demo.entity.Vendor;
import com.example.demo.entity.VendorPerformanceScore;
import com.example.demo.repository.DeliveryEvaluationRepository;
import com.example.demo.repository.VendorPerformanceScoreRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.VendorPerformanceScoreService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service   // ✅ REQUIRED so Spring can create the bean
public class VendorPerformanceScoreServiceImpl
        implements VendorPerformanceScoreService {

    private final VendorPerformanceScoreRepository scoreRepo;
    private final DeliveryEvaluationRepository evalRepo;
    private final VendorRepository vendorRepo;

    // ✅ Constructor injection
    public VendorPerformanceScoreServiceImpl(
            VendorPerformanceScoreRepository scoreRepo,
            DeliveryEvaluationRepository evalRepo,
            VendorRepository vendorRepo) {
        this.scoreRepo = scoreRepo;
        this.evalRepo = evalRepo;
        this.vendorRepo = vendorRepo;
    }

    @Override
    public VendorPerformanceScore calculateScore(Long vendorId) {

        Vendor vendor = vendorRepo.findById(vendorId)
                .orElseThrow(() -> new IllegalArgumentException("not found"));

        if (!Boolean.TRUE.equals(vendor.getActive())) {
            throw new IllegalStateException("inactive");
        }

        List<DeliveryEvaluation> evaluations =
                evalRepo.findByVendorId(vendorId);

        if (evaluations.isEmpty()) {
            throw new IllegalStateException("No evaluations");
        }

        double onTimeRate = evaluations.stream()
                .filter(DeliveryEvaluation::getMeetsDeliveryTarget)
                .count() * 100.0 / evaluations.size();

        double qualityRate = evaluations.stream()
                .filter(DeliveryEvaluation::getMeetsQualityTarget)
                .count() * 100.0 / evaluations.size();

        double finalScore = (onTimeRate + qualityRate) / 2;

        VendorPerformanceScore score = new VendorPerformanceScore();
        score.setVendor(vendor);
        score.setScore(finalScore);
        score.setCalculatedAt(new Timestamp(System.currentTimeMillis()));

        return scoreRepo.save(score);
    }

    @Override
    public VendorPerformanceScore getLatestScore(Long vendorId) {
        return scoreRepo.findTopByVendorIdOrderByCalculatedAtDesc(vendorId)
                .orElseThrow(() -> new IllegalArgumentException("not found"));
    }

    @Override
    public List<VendorPerformanceScore> getScoreHistory(Long vendorId) {
        return scoreRepo.findByVendorIdOrderByCalculatedAtDesc(vendorId);
    }
}
