package com.example.demo.repository;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.model.SLARequirement;
import com.example.demo.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DeliveryEvaluationRepository extends JpaRepository<DeliveryEvaluation, Long> {
    List<DeliveryEvaluation> findByVendorId(Long vendorId);
    List<DeliveryEvaluation> findBySlaRequirementId(Long slaRequirementId);

    // Example HQL-like methods
    List<DeliveryEvaluation> findHighQualityDeliveries(Vendor vendor, Double minScore);
    List<DeliveryEvaluation> findOnTimeDeliveries(SLARequirement slaRequirement);
}
