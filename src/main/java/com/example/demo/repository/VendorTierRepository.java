package com.example.demo.repository;

import com.example.demo.model.VendorTier;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface VendorTierRepository extends JpaRepository<VendorTier, Long> {
    boolean existsByTierName(String tierName);
    Optional<VendorTier> findByTierName(String tierName);
    List<VendorTier> findByActiveTrueOrderByMinOverallScoreDesc();
}
