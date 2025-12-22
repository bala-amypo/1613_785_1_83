package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.DeliveryEvaluationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryEvaluationServiceImpl implements DeliveryEvaluationService {

    private final DeliveryEvaluationRepository repo;
    private final VendorRepository vendorRepo;
    private final SLARequirementRepository slaRepo;

    public DeliveryEvaluationServiceImpl(
            DeliveryEvaluationRepository repo,
            VendorRepository vendorRepo,
            SLARequirementRepository slaRepo) {
        this.repo = repo;
        this.vendorRepo = vendorRepo;
        this.slaRepo = slaRepo;
    }

    @Override
    public DeliveryEvaluation createEvaluation(DeliveryEvaluation e) {

        Vendor vendor = e.getVendor();
        if (!vendor.getActive()) {
            throw new IllegalStateException("active vendors");
        }

        if (e.getActualDeliveryDays() < 0) {
            throw new IllegalArgumentException(">= 0");
        }

        if (e.getQualityScore() < 0 || e.getQualityScore() > 100) {
            throw new IllegalArgumentException("Quality score");
        }

        SLARequirement sla = e.getSlaRequirement();
        e.setMeetsDeliveryTarget(
                e.getActualDeliveryDays() <= sla.getMaxDeliveryDays());
        e.setMeetsQualityTarget(
                e.getQualityScore() >= sla.getMinQualityScore());

        return repo.save(e);
    }

    @Override
    public DeliveryEvaluation getEvaluationById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found"));
    }

    @Override
    public List<DeliveryEvaluation> getEvaluationsForVendor(Long vendorId) {
        return repo.findByVendorId(vendorId);
    }

    @Override
    public List<DeliveryEvaluation> getEvaluationsForRequirement(Long requirementId) {
        return repo.findByRequirementId(requirementId);
    }
}
