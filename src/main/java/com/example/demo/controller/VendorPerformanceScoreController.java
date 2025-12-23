package com.example.demo.controller;

import com.example.demo.entity.VendorPerformanceScore;
import com.example.demo.service.VendorPerformanceScoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendor-scores")
public class VendorPerformanceScoreController {

    private final VendorPerformanceScoreService scoreService;

    public VendorPerformanceScoreController(VendorPerformanceScoreService scoreService) {
        this.scoreService = scoreService;
    }

    // CREATE / CALCULATE SCORE
    @PostMapping("/{vendorId}")
    public VendorPerformanceScore calculateScore(
            @PathVariable Long vendorId,
            @RequestParam double score) {

        return scoreService.calculateScore(vendorId, score);
    }

    // GET LATEST SCORE
    @GetMapping("/{vendorId}/latest")
    public VendorPerformanceScore getLatestScore(@PathVariable Long vendorId) {
        return scoreService.getLatestScore(vendorId);
    }

    // GET ALL SCORES
    @GetMapping("/{vendorId}")
    public List<VendorPerformanceScore> getScores(@PathVariable Long vendorId) {
        return scoreService.getScoresForVendor(vendorId);
    }
}
