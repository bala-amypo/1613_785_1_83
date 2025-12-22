package com.example.demo.service.impl;

import com.example.demo.entity.SLARequirement;
import com.example.demo.repository.SLARequirementRepository;
import com.example.demo.service.SLARequirementService;
import java.util.List;

public class SLARequirementServiceImpl implements SLARequirementService {

    private final SLARequirementRepository repository;

    public SLARequirementServiceImpl(SLARequirementRepository repository) {
        this.repository = repository;
    }

    @Override
    public SLARequirement createRequirement(SLARequirement requirement) {
        return repository.save(requirement);
    }

    @Override
    public SLARequirement updateRequirement(Long id, SLARequirement requirement) {
        // update logic
        return requirement;
    }

    @Override
    public SLARequirement getRequirementById(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalStateException("SLARequirement not found"));
    }

    @Override
    public List<SLARequirement> getAllRequirements() {
        return repository.findAll();
    }

    @Override
    public void deactivateRequirement(Long id) {
        SLARequirement req = getRequirementById(id);
        req.setActive(false);
        repository.save(req);
    }
}
