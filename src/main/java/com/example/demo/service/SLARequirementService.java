package com.example.demo.service;

import com.example.demo.entity.SLARequirement;
import java.util.List;

public interface SLARequirementService {
    SLARequirement createRequirement(SLARequirement req);
    SLARequirement getRequirementById(Long id);
    List<SLARequirement> getAllRequirements();
    SLARequirement deactivateRequirement(Long id);
}
