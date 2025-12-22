package com.example.demo.service;

import com.example.demo.entity.SLARequirement;
import java.util.List;

public interface SLARequirementService {
    List<SLARequirement> getAllSLARequirements();
    SLARequirement getSLARequirementById(Long id);
    SLARequirement createSLARequirement(SLARequirement slaRequirement);
}
