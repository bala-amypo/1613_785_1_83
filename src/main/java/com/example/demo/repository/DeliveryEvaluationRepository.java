package com.example.demo.repository;

import com.example.demo.model.DeliveryEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryEvaluationRepository
        extends JpaRepository<DeliveryEvaluation, Long> {
}
