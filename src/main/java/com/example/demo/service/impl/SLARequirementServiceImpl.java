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
            throw new IllegalArgumentException("between 0 and 100");
        }

        return repo.save(req);
    }

    @Override
    public List<SLARequirement> getAll() {
        return repo.findAll();
    }

    @Override
    public SLARequirement getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found"));
    }

    @Override
    public SLARequirement update(Long id, SLARequirement req) {
        SLARequirement r = getById(id);
        r.setDescription(req.getDescription());
        r.setMaxDeliveryDays(req.getMaxDeliveryDays());
        r.setMinQualityScore(req.getMinQualityScore());
        return repo.save(r);
    }

    @Override
    public void deactivate(Long id) {
        SLARequirement r = getById(id);
        r.setActive(false);
        repo.save(r);
    }
}
