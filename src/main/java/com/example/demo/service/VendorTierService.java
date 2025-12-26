package com.example.demo.service;

import com.example.demo.model.VendorTier;

public interface VendorTierService {

    VendorTier createTier(VendorTier tier);

    void deactivateTier(Long id);
}
