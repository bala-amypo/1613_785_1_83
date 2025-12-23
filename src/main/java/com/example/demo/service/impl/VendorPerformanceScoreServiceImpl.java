package com.example.demo.service.impl;

import com.example.demo.entity.Vendor;
import com.example.demo.entity.VendorPerformanceScore;
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
    private final VendorRepository vendorRepo;

    public VendorPerformanceScoreServiceImpl(
            VendorPerformanceScoreRepository scoreRepo,
            VendorRepository vendorRepo) {
        this.scoreRepo = scoreRepo;
        this.vendorRepo = vendorRepo;
    }

    @Override
    public VendorPerformanceScore calculateScore(Long vendorId, double scoreValue) {

        Vendor vendor = vendorRepo.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        VendorPerformanceScore score = new VendorPerformanceScore();
        score.setVendor(vendor);
        score.setScore(scoreValue);
        score.setCalculatedAt(new Timestamp(System.currentTimeMillis()));

        return scoreRepo.save(score);
    }

    @Override
    public VendorPerformanceScore getLatestScore(Long vendorId) {
        List<VendorPerformanceScore> scores =
                scoreRepo.findByVendor_IdOrderByCalculatedAtDesc(vendorId);

        return scores.isEmpty() ? null : scores.get(0);
    }

    @Override
    public List<VendorPerformanceScore> getScoresForVendor(Long vendorId) {
        return scoreRepo.findByVendor_IdOrderByCalculatedAtDesc(vendorId);
    }
}
