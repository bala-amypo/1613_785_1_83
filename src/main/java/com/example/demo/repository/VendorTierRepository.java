package com.example.demo.repository;

import com.example.demo.model.VendorTier;

import java.util.List;
import java.util.Optional;

public interface VendorTierRepository {

    boolean existsByTierName(String name);

    VendorTier save(VendorTier tier);

    Optional<VendorTier> findById(Long id);

    List<VendorTier> findByActiveTrueOrderByMinScoreThresholdDesc();
}
