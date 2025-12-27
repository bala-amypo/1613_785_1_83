package com.example.demo.service.impl;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.model.SLARequirement;
import com.example.demo.model.Vendor;
import com.example.demo.repository.DeliveryEvaluationRepository;
import com.example.demo.repository.SLARequirementRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.DeliveryEvaluationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryEvaluationServiceImpl implements DeliveryEvaluationService {

    private final DeliveryEvaluationRepository evaluationRepo;
    private final VendorRepository vendorRepo;
    private final SLARequirementRepository slaRepo;

    public DeliveryEvaluationServiceImpl(DeliveryEvaluationRepository evaluationRepo,
                                         VendorRepository vendorRepo,
                                         SLARequirementRepository slaRepo) {
        this.evaluationRepo = evaluationRepo;
        this.vendorRepo = vendorRepo;
        this.slaRepo = slaRepo;
    }

    @Override
    public DeliveryEvaluation createEvaluation(DeliveryEvaluation eval) {
        Vendor vendor = vendorRepo.findById(eval.getVendor().getId())
                .orElseThrow(() -> new IllegalArgumentException("Vendor not found"));
        if (!vendor.getActive()) throw new IllegalStateException("Only active vendors allowed");

        SLARequirement sla = slaRepo.findById(eval.getSlaRequirement().getId())
                .orElseThrow(() -> new IllegalArgumentException("SLA Requirement not found"));
        if (!sla.getActive()) throw new IllegalStateException("Only active SLA allowed");

        if (eval.getActualDeliveryDays() < 0)
            throw new IllegalArgumentException("Delivery days must be >= 0");

        if (eval.getQualityScore() < 0 || eval.getQualityScore() > 100)
            throw new IllegalArgumentException("Quality score must be between 0 and 100");

        eval.setMeetsDeliveryTarget(eval.getActualDeliveryDays() <= sla.getMaxDeliveryDays());
        eval.setMeetsQualityTarget(eval.getQualityScore() >= sla.getQualityScoreThreshold());

        return evaluationRepo.save(eval);
    }

    @Override
    public List<DeliveryEvaluation> getEvaluationsForVendor(Long vendorId) {
        return evaluationRepo.findByVendorId(vendorId);
    }

    @Override
    public List<DeliveryEvaluation> getEvaluationsForRequirement(Long requirementId) {
        return evaluationRepo.findBySlaRequirementId(requirementId);
    }
}
