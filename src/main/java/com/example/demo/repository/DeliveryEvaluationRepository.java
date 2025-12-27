package com.example.demo.repository;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.model.SLARequirement;
import com.example.demo.model.Vendor;

import java.util.List;

public interface DeliveryEvaluationRepository {

    DeliveryEvaluation save(DeliveryEvaluation evaluation);

    List<DeliveryEvaluation> findByVendorId(Long vendorId);

    List<DeliveryEvaluation> findBySlaRequirementId(Long slaRequirementId);

    List<DeliveryEvaluation> findHighQualityDeliveries(Vendor vendor, Double minQuality);

    List<DeliveryEvaluation> findOnTimeDeliveries(SLARequirement slaRequirement);
}
