package com.example.demo.service.impl;

import com.example.demo.entity.SLARequirement;
import com.example.demo.repository.SLARequirementRepository;
import com.example.demo.service.SLARequirementService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SLARequirementServiceImpl implements SLARequirementService {

    private final SLARequirementRepository slaRepo;

    public SLARequirementServiceImpl(SLARequirementRepository slaRepo) {
        this.slaRepo = slaRepo;
    }

    @Override
    public SLARequirement createRequirement(SLARequirement req) {
        if(slaRepo.existsByRequirementName(req.getRequirementName())) {
            throw new IllegalArgumentException("unique");
        }
        if(req.getMaxDeliveryDays() < 0) throw new IllegalArgumentException("Max delivery days >= 0");
        if(req.getMinQualityScore() < 0 || req.getMinQualityScore() > 100)
            throw new IllegalArgumentException("Quality score between 0 and 100");
        return slaRepo.save(req);
    }

    @Override
    public SLARequirement getRequirementById(Long id) {
        return slaRepo.findById(id).orElseThrow(() -> new IllegalStateException("not found"));
    }

    @Override
    public List<SLARequirement> getAllRequirements() {
        return slaRepo.findAll();
    }

    @Override
    public SLARequirement deactivateRequirement(Long id) {
        SLARequirement r = getRequirementById(id);
        r.setActive(false);
        return slaRepo.save(r);
    }
}
