package com.example.demo.controller;

import com.example.demo.entity.VendorPerformanceScore;
import com.example.demo.service.impl.VendorPerformanceScoreServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendor-scores")
public class VendorPerformanceScoreController {

    private final VendorPerformanceScoreServiceImpl service;

    public VendorPerformanceScoreController(VendorPerformanceScoreServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/{vendorId}")
    public List<VendorPerformanceScore> getScores(@PathVariable Long vendorId) {
        return service.getScoresByVendor(vendorId);
    }

    @PostMapping
    public VendorPerformanceScore saveScore(@RequestBody VendorPerformanceScore score) {
        return service.saveScore(score);
    }
}
