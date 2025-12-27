package com.example.demo.controller;

import com.example.demo.model.VendorPerformanceScore;
import com.example.demo.service.VendorPerformanceScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendor-scores")
public class VendorPerformanceScoreController {

    private final VendorPerformanceScoreService service;

    public VendorPerformanceScoreController(VendorPerformanceScoreService service) {
        this.service = service;
    }

    @PostMapping("/{vendorId}")
    public ResponseEntity<VendorPerformanceScore> calculate(@PathVariable Long vendorId) {
        return ResponseEntity.ok(service.calculateScore(vendorId));
    }

    @GetMapping("/{vendorId}/latest")
    public ResponseEntity<VendorPerformanceScore> latest(@PathVariable Long vendorId) {
        return ResponseEntity.ok(service.getLatestScore(vendorId));
    }

    @GetMapping("/{vendorId}")
    public ResponseEntity<List<VendorPerformanceScore>> history(@PathVariable Long vendorId) {
        return ResponseEntity.ok(service.getScoresForVendor(vendorId));
    }
}
