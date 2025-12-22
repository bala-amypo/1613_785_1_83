package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.DeliveryEvaluationService;
import org.springframework.stereotype.Service;

@Service
public class DeliveryEvaluationServiceImpl
        implements DeliveryEvaluationService {

    private final DeliveryEvaluationRepository evaluationRepository;
    private final VendorRepository vendorRepository;
    private final SLARequirementRepository slaRepository;

    public DeliveryEvaluationServiceImpl(
            DeliveryEvaluationRepository evaluationRepository,
            VendorRepository vendorRepository,
            SLARequirementRepository slaRepository) {

        this.evaluationRepository = evaluationRepository;
        this.vendorRepository = vendorRepository;
        this.slaRepository = slaRepository;
    }

    public DeliveryEvaluation createEvaluation(DeliveryEvaluation evaluation) {

        if (!evaluation.getVendor().getActive()) {
            throw new IllegalStateException("active vendors");
        }

        if (evaluation.getActualDeliveryDays() < 0) {
            throw new IllegalArgumentException(">= 0");
        }

        if (evaluation.getQualityScore() < 0 || evaluation.getQualityScore() > 100) {
            throw new IllegalArgumentException("Quality score");
        }

        SLARequirement sla = evaluation.getSlaRequirement();

        evaluation.setMeetsDeliveryTarget(
                evaluation.getActualDeliveryDays() <= sla.getMaxDeliveryDays());

        evaluation.setMeetsQualityTarget(
                evaluation.getQualityScore() >= sla.getMinQualityScore());

        return evaluationRepository.save(evaluation);
    }
}
