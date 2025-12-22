package com.example.demo.controller;

import com.example.demo.entity.VendorTier;
import com.example.demo.service.VendorTierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tiers")
public class VendorTierController {

    private final VendorTierService tierService;

    public VendorTierController(VendorTierService tierService) {
        this.tierService = tierService;
    }

    @PostMapping("/")
    public ResponseEntity<VendorTier> createTier(@RequestBody VendorTier tier) {
        return ResponseEntity.ok(tierService.createTier(tier));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendorTier> updateTier(@PathVariable Long id, @RequestBody VendorTier tier) {
        return ResponseEntity.ok(tierService.updateTier(id, tier));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorTier> getTier(@PathVariable Long id) {
        return ResponseEntity.ok(tierService.getTierById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<VendorTier>> getAllTiers() {
        return ResponseEntity.ok(tierService.getAllTiers());
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivateTier(@PathVariable Long id) {
        tierService.deactivateTier(id);
        return ResponseEntity.ok("Vendor Tier deactivated");
    }
}
