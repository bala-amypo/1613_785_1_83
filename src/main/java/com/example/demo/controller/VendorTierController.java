package com.example.demo.controller;

import com.example.demo.entity.VendorTier;
import com.example.demo.service.VendorTierService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tiers")
@Tag(name = "Vendor Tiers", description = "Manage vendor tiers")
public class VendorTierController {

    private final VendorTierService tierService;

    public VendorTierController(VendorTierService tierService) {
        this.tierService = tierService;
    }

    @PostMapping
    public VendorTier createTier(@RequestBody VendorTier tier) {
        return tierService.createTier(tier);
    }

    @GetMapping("/{id}")
    public VendorTier getTier(@PathVariable Long id) {
        return tierService.getTierById(id);
    }

    @GetMapping
    public List<VendorTier> getAllTiers() {
        return tierService.getAllTiers();
    }

    @PutMapping("/{id}")
    public VendorTier updateTier(@PathVariable Long id, @RequestBody VendorTier tier) {
        return tierService.updateTier(id, tier);
    }

    @PutMapping("/{id}/deactivate")
    public VendorTier deactivateTier(@PathVariable Long id) {
        return tierService.deactivateTier(id);
    }
}
