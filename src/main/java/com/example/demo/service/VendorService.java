package com.example.demo.service;

import com.example.demo.entity.VendorTier;
import java.util.List;

public interface VendorTierService {

    VendorTier createTier(VendorTier tier);

    List<VendorTier> getAll();

    VendorTier getById(Long id);
}
