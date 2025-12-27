package com.example.demo.repository;

import com.example.demo.model.Vendor;

import java.util.List;
import java.util.Optional;

public interface VendorRepository {

    boolean existsByName(String name);

    Optional<Vendor> findById(Long id);

    List<Vendor> findAll();

    Vendor save(Vendor vendor);
}
