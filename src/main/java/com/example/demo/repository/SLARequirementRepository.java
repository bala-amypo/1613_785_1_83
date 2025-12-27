package com.example.demo.repository;

import com.example.demo.model.SLARequirement;

import java.util.Optional;

public interface SLARequirementRepository {

    boolean existsByRequirementName(String name);

    Optional<SLARequirement> findById(Long id);

    SLARequirement save(SLARequirement requirement);
}
