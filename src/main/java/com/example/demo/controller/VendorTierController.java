package com.example.demo.controller;

import com.example.demo.model.VendorTier;
import com.example.demo.service.VendorTierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendor-tiers")
public class VendorTierController {

    private final VendorTierService vendorTierService;

    public VendorTierController(VendorTierService vendorTierService) {
        this.vendorTierService = vendorTierService;
    }

    @PostMapping
    public ResponseEntity<VendorTier> createTier(@RequestBody VendorTier tier) {
        return ResponseEntity.ok(vendorTierService.createTier(tier));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorTier> getTier(@PathVariable Long id) {
        return ResponseEntity.ok(vendorTierService.getTierById(id));
    }

    @GetMapping
    public ResponseEntity<List<VendorTier>> getAllTiers() {
        return ResponseEntity.ok(vendorTierService.getAllTiers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivateTier(@PathVariable Long id) {
        vendorTierService.deactivateTier(id);
        return ResponseEntity.noContent().build();
    }
}
