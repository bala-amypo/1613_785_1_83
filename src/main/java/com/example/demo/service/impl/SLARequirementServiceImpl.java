package com.example.demo.service.impl;

import com.example.demo.model.SLARequirement;
import com.example.demo.repository.SLARequirementRepository;
import com.example.demo.service.SLARequirementService;

public class SLARequirementServiceImpl implements SLARequirementService {

    private final SLARequirementRepository repo;

    public SLARequirementServiceImpl(SLARequirementRepository repo) {
        this.repo = repo;
    }

    @Override
    public SLARequirement createRequirement(SLARequirement req) {

        if (req.getMaxDeliveryDays() == null || req.getMaxDeliveryDays() <= 0) {
            throw new IllegalArgumentException("Max delivery days must be > 0");
        }

        if (req.getMinQualityScore() < 0 || req.getMinQualityScore() > 100) {
            throw new IllegalArgumentException("Quality score must be between 0 and 100");
        }

        if (repo.existsByRequirementName(req.getRequirementName())) {
            throw new IllegalArgumentException("Requirement name must be unique");
        }

        return repo.save(req);
    }

    @Override
    public SLARequirement updateRequirement(Long id, SLARequirement update) {
        SLARequirement existing = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("SLA not found"));

        if (update.getRequirementName() != null &&
                repo.existsByRequirementName(update.getRequirementName())) {
            throw new IllegalArgumentException("Requirement name must be unique");
        }

        if (update.getRequirementName() != null) {
            existing.setRequirementName(update.getRequirementName());
        }

        return repo.save(existing);
    }

    @Override
    public void deactivateRequirement(Long id) {
        SLARequirement req = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("SLA not found"));
        req.setActive(false);
        repo.save(req);
    }
}
