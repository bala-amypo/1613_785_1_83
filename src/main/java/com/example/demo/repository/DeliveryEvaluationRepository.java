package com.example.demo.repository;

import com.example.demo.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface DeliveryEvaluationRepository
        extends JpaRepository<DeliveryEvaluation, Long> {

    @Query("select e from DeliveryEvaluation e where e.vendor.id = ?1")
    List<DeliveryEvaluation> findByVendorId(Long vendorId);

    @Query("select e from DeliveryEvaluation e where e.slaRequirement.id = ?1")
    List<DeliveryEvaluation> findByRequirementId(Long reqId);
}
