package com.example.demo.controller;

import com.example.demo.model.VendorTier;
import com.example.demo.service.VendorTierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendor-tiers")
public class VendorTierController {

    private final VendorTierService service;

    public VendorTierController(VendorTierService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<VendorTier> create(@RequestBody VendorTier tier) {
        return ResponseEntity.ok(service.createTier(tier));
    }

    @GetMapping
    public ResponseEntity<List<VendorTier>> getAll() {
        return ResponseEntity.ok(service.getAllTiers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        service.deactivateTier(id);
        return ResponseEntity.noContent().build();
    }
}
