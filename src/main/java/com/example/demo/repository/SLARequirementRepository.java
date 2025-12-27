package com.example.demo.repository;

import com.example.demo.model.SLARequirement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SLARequirementRepository extends JpaRepository<SLARequirement, Long> {
    boolean existsByRequirementName(String requirementName);
    Optional<SLARequirement> findByRequirementName(String requirementName);
}
