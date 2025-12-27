package com.example.demo.repository;

import java.util.Optional;

public interface VendorPerformanceScoreRepository {
    Optional<Double> findAverageScoreByVendorId(Long vendorId);
}
