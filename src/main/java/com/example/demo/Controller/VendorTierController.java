package com.example.demo.controller;

import com.example.demo.entity.VendorTier;
import com.example.demo.service.VendorTierService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendor-tier")
public class VendorTierController {

    private final VendorTierService service;

    public VendorTierController(VendorTierService service) {
        this.service = service;
    }

    @GetMapping
    public List<VendorTier> getAll() {
        return service.getAllVendorTiers();
    }

    @PostMapping
    public VendorTier create(@RequestBody VendorTier vendorTier) {
        return service.createVendorTier(vendorTier);
    }
}
