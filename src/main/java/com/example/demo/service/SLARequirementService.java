package com.example.demo.service.impl;

import com.example.demo.entity.SLARequirement;
import com.example.demo.repository.SLARequirementRepository;
import com.example.demo.service.SLARequirementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SLARequirementServiceImpl implements SLARequirementService {

    private final SLARequirementRepository repo;

    public SLARequirementServiceImpl(SLARequirementRepository repo) {
        this.repo = repo;
    }

    public SLARequirement createRequirement(SLARequirement req) {
        if (req.getMaxDeliveryDays() <= 0)
            throw new IllegalArgumentException("Max delivery days");

        if (req.getMinQualityScore() < 0 || req.getMinQualityScore() > 100)
            throw new IllegalArgumentException("between 0 and 100");

        return repo.save(req);
    }

    public SLARequirement updateRequirement(Long id, SLARequirement req) {
        SLARequirement existing = getRequirementById(id);
        existing.setDescription(req.getDescription());
        existing.setMaxDeliveryDays(req.getMaxDeliveryDays());
        existing.setMinQualityScore(req.getMinQualityScore());
        return repo.save(existing);
    }

    public SLARequirement getRequirementById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found"));
    }

    public List<SLARequirement> getAllRequirements() {
        return repo.findAll();
    }

    public void deactivateRequirement(Long id) {
        SLARequirement r = getRequirementById(id);
        r.setActive(false);
        repo.save(r);
    }
}
