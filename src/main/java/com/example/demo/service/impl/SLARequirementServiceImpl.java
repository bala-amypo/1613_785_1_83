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

    @Override
    public SLARequirement createRequirement(SLARequirement req) {
        if (repo.existsByRequirementName(req.getRequirementName())) {
            throw new IllegalArgumentException("unique");
        }
        if (req.getMaxDeliveryDays() <= 0) {
            throw new IllegalArgumentException("Max delivery days");
        }
        if (req.getMinQualityScore() < 0 || req.getMinQualityScore() > 100) {
            throw new IllegalArgumentException("Quality score");
        }
        return repo.save(req);
    }

    @Override
    public SLARequirement updateRequirement(Long id, SLARequirement req) {
        SLARequirement r = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found"));

        r.setDescription(req.getDescription());
        r.setMaxDeliveryDays(req.getMaxDeliveryDays());
        r.setMinQualityScore(req.getMinQualityScore());

        return repo.save(r);
    }

    @Override
    public SLARequirement getRequirementById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found"));
    }

    @Override
    public List<SLARequirement> getAllRequirements() {
        return repo.findAll();
    }

    @Override
    public void deactivateRequirement(Long id) {
        SLARequirement r = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found"));
        r.setActive(false);
        repo.save(r);
    }
}
