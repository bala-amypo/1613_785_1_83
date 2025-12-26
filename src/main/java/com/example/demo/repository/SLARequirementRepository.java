package com.example.demo.repository;

import com.example.demo.model.SLARequirement;

import java.util.List;
import java.util.Optional;

public interface SLARequirementRepository {
    boolean existsByRequirementName(String name);
    SLARequirement save(SLARequirement req);
    Optional<SLARequirement> findById(Long id);
    List<SLARequirement> findAll();
}
