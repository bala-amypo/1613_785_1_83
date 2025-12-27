package com.example.demo.repository;

import com.example.demo.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
    boolean existsByName(String name);
    Optional<Vendor> findByName(String name);
}
