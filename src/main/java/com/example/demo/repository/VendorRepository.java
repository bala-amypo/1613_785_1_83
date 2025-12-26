package com.example.demo.repository;

import com.example.demo.model.Vendor;
import java.util.*;

public interface VendorRepository {
    boolean existsByName(String name);
    Vendor save(Vendor v);
    Optional<Vendor> findById(Long id);
    List<Vendor> findAll();
}
