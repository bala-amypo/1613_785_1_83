package com.example.demo.service.impl;

import com.example.demo.model.SLARequirement;
import com.example.demo.repository.SLARequirementRepository;
import com.example.demo.service.SLARequirementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SLARequirementServiceImpl implements SLARequirementService {

    private final SLARequirementRepository slaRequirementRepository;

    public SLARequirementServiceImpl(SLARequirementRepository slaRequirementRepository) {
        this.slaRequirementRepository = slaRequirementRepository;
    }

    @Override
    public SLARequirement createRequirement(SLARequirement requirement) {
        if (requirement.getMaxDeliveryDays() <= 0) {
            throw new IllegalArgumentException("Max delivery days must be > 0");
        }
        if (requirement.getQualityScore() < 0 || requirement.getQualityScore() > 100) {
            throw new IllegalArgumentException("Quality score must be between 0 and 100");
        }
        if (slaRequirementRepository.existsByRequirementName(requirement.getRequirementName())) {
            throw new IllegalArgumentException("SLA Requirement name must be unique");
        }
        requirement.setActive(true);
        return slaRequirementRepository.save(requirement);
    }

    @Override
    public SLARequirement updateRequirement(Long id, SLARequirement update) {
        SLARequirement existing = slaRequirementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("SLA Requirement not found"));
        if (update.getRequirementName() != null && !update.getRequirementName().equals(existing.getRequirementName())) {
            if (slaRequirementRepository.existsByRequirementName(update.getRequirementName())) {
                throw new IllegalArgumentException("SLA Requirement name must be unique");
            }
            existing.setRequirementName(update.getRequirementName());
        }
        if (update.getMaxDeliveryDays() > 0) existing.setMaxDeliveryDays(update.getMaxDeliveryDays());
        if (update.getQualityScore() >= 0 && update.getQualityScore() <= 100) existing.setQualityScore(update.getQualityScore());
        if (update.getDescription() != null) existing.setDescription(update.getDescription());

        return slaRequirementRepository.save(existing);
    }

    @Override
    public SLARequirement getRequirementById(Long id) {
        return slaRequirementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("SLA Requirement not found"));
    }

    @Override
    public List<SLARequirement> getAllRequirements() {
        return slaRequirementRepository.findAll();
    }

    @Override
    public void deactivateRequirement(Long id) {
        SLARequirement existing = slaRequirementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("SLA Requirement not found"));
        existing.setActive(false);
        slaRequirementRepository.save(existing);
    }
}
