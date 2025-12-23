package com.example.demo.service.impl;

import com.example.demo.entity.DeliveryEvaluation;
import com.example.demo.entity.SLARequirement;
import com.example.demo.entity.Vendor;
import com.example.demo.repository.DeliveryEvaluationRepository;
import com.example.demo.repository.SLARequirementRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.DeliveryEvaluationService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DeliveryEvaluationServiceImpl implements DeliveryEvaluationService {

    private final DeliveryEvaluationRepository evalRepo;
    private final VendorRepository vendorRepo;
    private final SLARequirementRepository slaRepo;

    public DeliveryEvaluationServiceImpl(DeliveryEvaluationRepository evalRepo,
                                         VendorRepository vendorRepo,
                                         SLARequirementRepository slaRepo) {
        this.evalRepo = evalRepo;
        this.vendorRepo = vendorRepo;
        this.slaRepo = slaRepo;
    }

    @Override
    public DeliveryEvaluation createEvaluation(DeliveryEvaluation evaluation) {
        Vendor vendor = vendorRepo.findById(evaluation.getVendor().getId())
                .orElseThrow(() -> new IllegalStateException("not found"));
        if(!vendor.getActive()) throw new IllegalStateException("active vendors");

        SLARequirement sla = slaRepo.findById(evaluation.getSlaRequirement().getId())
                .orElseThrow(() -> new IllegalStateException("not found"));

        if(evaluation.getActualDeliveryDays() < 0) throw new IllegalArgumentException("Max delivery days >= 0");
        if(evaluation.getQualityScore() < 0 || evaluation.getQualityScore() > 100)
            throw new IllegalArgumentException("Quality score between 0 and 100");

        evaluation.setMeetsDeliveryTarget(evaluation.getActualDeliveryDays() <= sla.getMaxDeliveryDays());
        evaluation.setMeetsQualityTarget(evaluation.getQualityScore() >= sla.getMinQualityScore());

        return evalRepo.save(evaluation);
    }

    @Override
    public DeliveryEvaluation getEvaluationById(Long id) {
        return evalRepo.findById(id).orElseThrow(() -> new IllegalStateException("not found"));
    }

    @Override
    public List<DeliveryEvaluation> getEvaluationsForVendor(Long vendorId) {
        return evalRepo.findByVendorId(vendorId);
    }

    @Override
    public List<DeliveryEvaluation> getEvaluationsForRequirement(Long requirementId) {
        return evalRepo.findBySlaRequirementId(requirementId);
    }
}
