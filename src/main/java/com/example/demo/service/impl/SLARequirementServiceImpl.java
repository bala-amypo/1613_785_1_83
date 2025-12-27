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
        if (slaRequirementRepository.existsByRequirementName(requirement.getRequirementName())) {
            throw new IllegalArgumentException("SLA Requirement name must be unique");
        }
        if (requirement.getMaxDeliveryDays() == null || requirement.getMaxDeliveryDays() <= 0) {
            throw new IllegalArgumentException("Max delivery days must be >= 1");
        }
        if (requirement.getQualityScoreThreshold() < 0 || requirement.getQualityScoreThreshold() > 100) {
            throw new IllegalArgumentException("Quality score must be between 0 and 100");
        }
        requirement.setActive(true);
        return slaRequirementRepository.save(requirement);
    }

    @Override
    public SLARequirement updateRequirement(Long id, SLARequirement requirement) {
        SLARequirement existing = slaRequirementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("SLA Requirement not found"));

        if (requirement.getRequirementName() != null && !requirement.getRequirementName().equals(existing.getRequirementName())) {
            if (slaRequirementRepository.existsByRequirementName(requirement.getRequirementName())) {
                throw new IllegalArgumentException("SLA Requirement name must be unique");
            }
            existing.setRequirementName(requirement.getRequirementName());
        }

        if (requirement.getMaxDeliveryDays() != null) existing.setMaxDeliveryDays(requirement.getMaxDeliveryDays());
        if (requirement.getQualityScoreThreshold() != null) existing.setQualityScoreThreshold(requirement.getQualityScoreThreshold());
        if (requirement.getDescription() != null) existing.setDescription(requirement.getDescription());

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
