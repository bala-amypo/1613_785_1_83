package com.example.demo.service;

import com.example.demo.entity.VendorTier;
import java.util.List;

public interface VendorTierService {
    List<VendorTier> getAllVendorTiers();
    VendorTier getVendorTierById(Long id);
    VendorTier createVendorTier(VendorTier vendorTier);
}
