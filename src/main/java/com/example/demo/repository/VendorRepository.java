package com.example.demo.repository;

import com.example.demo.model.Vendor;

import java.util.List;
import java.util.Optional;

public interface VendorRepository {

    boolean existsByName(String name);

    Vendor save(Vendor vendor);

    Optional<Vendor> findById(Long id);

    List<Vendor> findAll();
}
