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

@Service
public class VendorPerformanceScoreServiceImpl
        implements VendorPerformanceScoreService {

    private final VendorPerformanceScoreRepository scoreRepo;
    private final DeliveryEvaluationRepository evalRepo;
    private final VendorRepository vendorRepo;

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
                .orElseThrow(() -> new IllegalArgumentException("Vendor not found"));

        List<DeliveryEvaluation> evaluations =
                evalRepo.findByVendorId(vendorId);

        if (evaluations.isEmpty()) {
            throw new IllegalStateException("No evaluations found");
        }

        double onTime = evaluations.stream()
                .filter(DeliveryEvaluation::getMeetsDeliveryTarget)
                .count() * 100.0 / evaluations.size();

        double quality = evaluations.stream()
                .filter(DeliveryEvaluation::getMeetsQualityTarget)
                .count() * 100.0 / evaluations.size();

        VendorPerformanceScore score = new VendorPerformanceScore();
        score.setVendor(vendor);
        score.setScore((onTime + quality) / 2);
        score.setCalculatedAt(new Timestamp(System.currentTimeMillis()));

        return scoreRepo.save(score);
    }

    @Override
    public VendorPerformanceScore getLatestScore(Long vendorId) {
        return scoreRepo.findTopByVendorIdOrderByCalculatedAtDesc(vendorId)
                .orElseThrow(() -> new IllegalArgumentException("Score not found"));
    }

    @Override
    public List<VendorPerformanceScore> getScoresForVendor(Long vendorId) {
        return scoreRepo.findByVendorIdOrderByCalculatedAtDesc(vendorId);
    }
}
