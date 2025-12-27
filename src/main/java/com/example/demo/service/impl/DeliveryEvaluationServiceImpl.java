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
    private final DeliveryEvaluationRepository deliveryEvaluationRepository;
    private final VendorRepository vendorRepository;
    private final SLARequirementRepository slaRequirementRepository;

    public DeliveryEvaluationServiceImpl(DeliveryEvaluationRepository deliveryEvaluationRepository,
                                       VendorRepository vendorRepository,
                                       SLARequirementRepository slaRequirementRepository) {
        this.deliveryEvaluationRepository = deliveryEvaluationRepository;
        this.vendorRepository = vendorRepository;
        this.slaRequirementRepository = slaRequirementRepository;
    }

    @Override
    public DeliveryEvaluation createEvaluation(DeliveryEvaluation evaluation) {
        validateEvaluation(evaluation);
        DeliveryEvaluation saved = deliveryEvaluationRepository.save(evaluation);
        saved.setMeetsDeliveryTarget(evaluation.getActualDeliveryDays() <= 
            evaluation.getSlaRequirement().getMaxDeliveryDays());
        saved.setMeetsQualityTarget(evaluation.getQualityScore() >= 
            evaluation.getSlaRequirement().getMinQualityScore());
        return deliveryEvaluationRepository.save(saved);
    }

    @Override
    public List<DeliveryEvaluation> getEvaluationsForVendor(Long vendorId) {
        return deliveryEvaluationRepository.findByVendorId(vendorId);
    }

    @Override
    public List<DeliveryEvaluation> getEvaluationsForRequirement(Long slaId) {
        return deliveryEvaluationRepository.findBySlaRequirementId(slaId);
    }

    private void validateEvaluation(DeliveryEvaluation eval) {
        Vendor vendor = vendorRepository.findById(eval.getVendor().getId())
            .orElseThrow(() -> new IllegalStateException("Only active vendors can have evaluations"));
        if (!vendor.getActive()) {
            throw new IllegalStateException("Only active vendors can have evaluations");
        }
        
        SLARequirement sla = slaRequirementRepository.findById(eval.getSlaRequirement().getId())
            .orElseThrow(() -> new IllegalArgumentException("SLA requirement not found"));
        if (!sla.getActive()) {
            throw new IllegalStateException("Only active SLA requirements can be used");
        }
        
        if (eval.getActualDeliveryDays() == null || eval.getActualDeliveryDays() < 0) {
            throw new IllegalArgumentException("Delivery days must be >= 0");
        }
        if (eval.getQualityScore() == null || eval.getQualityScore() < 0 || eval.getQualityScore() > 100) {
            throw new IllegalArgumentException("Quality score must be between 0 and 100");
        }
    }
}
