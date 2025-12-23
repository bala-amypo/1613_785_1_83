package com.example.demo.service;

import com.example.demo.entity.SLARequirement;
import java.util.List;

public interface SLARequirementService {

    SLARequirement createRequirement(SLARequirement req);

    List<SLARequirement> getAll();

    SLARequirement getById(Long id);

    SLARequirement update(Long id, SLARequirement req);

    void deactivate(Long id);
}
