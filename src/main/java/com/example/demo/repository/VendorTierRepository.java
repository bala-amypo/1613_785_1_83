package com.example.demo.repository;

import com.example.demo.model.VendorTier;

import java.util.List;
import java.util.Optional;

public interface VendorTierRepository {

    boolean existsByTierName(String tierName);

    Optional<VendorTier> findById(Long id);

    List<VendorTier> findByActiveTrueOrderByMinScoreThresholdDesc();

    VendorTier save(VendorTier tier);
}
