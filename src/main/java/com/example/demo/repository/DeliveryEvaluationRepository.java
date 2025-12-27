package com.example.demo.repository;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.model.SLARequirement;
import com.example.demo.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface DeliveryEvaluationRepository extends JpaRepository<DeliveryEvaluation, Long> {
    List<DeliveryEvaluation> findByVendorIdOrderByEvaluationDateDesc(Long vendorId);
    List<DeliveryEvaluation> findByVendorId(Long vendorId);
    List<DeliveryEvaluation> findBySlaRequirementId(Long slaId);
    
    @Query("SELECT e FROM DeliveryEvaluation e WHERE e.vendor = :vendor AND e.qualityScore >= :threshold")
    List<DeliveryEvaluation> findHighQualityDeliveries(@Param("vendor") Vendor vendor, @Param("threshold") Double threshold);
    
    @Query("SELECT e FROM DeliveryEvaluation e WHERE e.slaRequirement = :sla AND e.meetsDeliveryTarget = true")
    List<DeliveryEvaluation> findOnTimeDeliveries(@Param("sla") SLARequirement sla);
}
