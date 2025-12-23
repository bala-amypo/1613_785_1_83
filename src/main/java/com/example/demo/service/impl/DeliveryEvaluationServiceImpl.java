package com.example.demo.service.impl;

import com.example.demo.entity.DeliveryEvaluation;
import com.example.demo.entity.SLARequirement;
import com.example.demo.repository.DeliveryEvaluationRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.repository.SLARequirementRepository;

import org.springframework.stereotype.Service;

@Service
public class DeliveryEvaluationServiceImpl {

    private final DeliveryEvaluationRepository evalRepo;
    private final VendorRepository vendorRepo;
    private final SLARequirementRepository slaRepo;

    public DeliveryEvaluationServiceImpl(
            DeliveryEvaluationRepository evalRepo,
            VendorRepository vendorRepo,
            SLARequirementRepository slaRepo) {
        this.evalRepo = evalRepo;
        this.vendorRepo = vendorRepo;
        this.slaRepo = slaRepo;
    }

    public DeliveryEvaluation createEvaluation(DeliveryEvaluation e) {

        if (!e.getVendor().getActive())
            throw new IllegalStateException("active vendors");

        if (e.getActualDeliveryDays() < 0)
            throw new IllegalArgumentException(">= 0");

        if (e.getQualityScore() < 0 || e.getQualityScore() > 100)
            throw new IllegalArgumentException("Quality score");

        SLARequirement sla = e.getSlaRequirement();

        e.setMeetsDeliveryTarget(
                e.getActualDeliveryDays() <= sla.getMaxDeliveryDays());

        e.setMeetsQualityTarget(
                e.getQualityScore() >= sla.getMinQualityScore());

        return evalRepo.save(e);
    }
}
