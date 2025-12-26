package com.example.demo.service;

import com.example.demo.model.SLARequirement;

public interface SLARequirementService {

    SLARequirement createRequirement(SLARequirement req);

    SLARequirement updateRequirement(Long id, SLARequirement update);

    void deactivateRequirement(Long id);
}
