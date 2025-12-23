package com.example.demo.controller;

import com.example.demo.entity.VendorPerformanceScore;
import com.example.demo.service.VendorPerformanceScoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendor-performance-scores")
public class VendorPerformanceScoreController {

    private final VendorPerformanceScoreService service;

    public VendorPerformanceScoreController(VendorPerformanceScoreService service) {
        this.service = service;
    }

    @GetMapping("/vendor/{vendorId}")
    public List<VendorPerformanceScore> getScores(@PathVariable Long vendorId) {
        return service.getScoresForVendor(vendorId);
    }

    // (optional but useful)
    @PostMapping("/calculate/{vendorId}")
    public VendorPerformanceScore calculate(@PathVariable Long vendorId) {
        return service.calculateScore(vendorId);
    }
}
