package com.example.demo.service;

import com.example.demo.model.VendorTier;
import java.util.List;

public interface VendorTierService {
    VendorTier createTier(VendorTier tier);
    VendorTier getTierById(Long id);
    List<VendorTier> getAllTiers();
    void deactivateTier(Long id);
}
