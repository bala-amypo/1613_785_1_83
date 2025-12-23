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

    @PostMapping("/{vendorId}")
    public VendorPerformanceScore calculateScore(
            @PathVariable Long vendorId,
            @RequestParam double score) {

        return scoreService.calculateScore(vendorId, score);
    }

    @GetMapping("/{vendorId}/latest")
    public VendorPerformanceScore getLatestScore(@PathVariable Long vendorId) {
        return scoreService.getLatestScore(vendorId);
    }

    @GetMapping("/{vendorId}")
    public List<VendorPerformanceScore> getScores(@PathVariable Long vendorId) {
        return scoreService.getScoresForVendor(vendorId);
    }
}
