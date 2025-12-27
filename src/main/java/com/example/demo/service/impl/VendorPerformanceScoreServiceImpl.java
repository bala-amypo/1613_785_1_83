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

    private final VendorPerformanceScoreRepository scoreRepository;
    private final DeliveryEvaluationRepository evaluationRepository;
    private final VendorRepository vendorRepository;
    private final VendorTierRepository tierRepository;

    public VendorPerformanceScoreServiceImpl(
            VendorPerformanceScoreRepository scoreRepository,
            DeliveryEvaluationRepository evaluationRepository,
            VendorRepository vendorRepository,
            VendorTierRepository tierRepository
    ) {
        this.scoreRepository = scoreRepository;
        this.evaluationRepository = evaluationRepository;
        this.vendorRepository = vendorRepository;
        this.tierRepository = tierRepository;
    }

    @Override
    public VendorPerformanceScore calculateScore(Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new IllegalArgumentException("Vendor not found"));

        List<DeliveryEvaluation> evaluations = evaluationRepository.findByVendorId(vendorId);

        long total = evaluations.size();
        long onTime = evaluations.stream().filter(DeliveryEvaluation::getMeetsDeliveryTarget).count();
        long quality = evaluations.stream().filter(DeliveryEvaluation::getMeetsQualityTarget).count();

        double onTimePercent = total == 0 ? 0 : (onTime * 100.0 / total);
        double qualityPercent = total == 0 ? 0 : (quality * 100.0 / total);
        double overall = (onTimePercent + qualityPercent) / 2.0;

        VendorPerformanceScore score = new VendorPerformanceScore(vendor, onTimePercent, qualityPercent, overall);
        score.setCalculatedAt(LocalDate.now());

        return scoreRepository.save(score);
    }

    @Override
    public VendorPerformanceScore getLatestScore(Long vendorId) {
        List<VendorPerformanceScore> scores = scoreRepository.findByVendorOrderByCalculatedAtDesc(vendorId);
        if (scores.isEmpty()) return null;
        return scores.get(0);
    }

    @Override
    public List<VendorPerformanceScore> getScoresForVendor(Long vendorId) {
        return scoreRepository.findByVendorOrderByCalculatedAtDesc(vendorId);
    }
}
