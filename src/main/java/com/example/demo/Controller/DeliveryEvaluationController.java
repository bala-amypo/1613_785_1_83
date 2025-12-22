package com.example.demo.controller;

import com.example.demo.entity.DeliveryEvaluation;
import com.example.demo.service.DeliveryEvaluationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluations")
public class DeliveryEvaluationController {

    private final DeliveryEvaluationService evaluationService;

    public DeliveryEvaluationController(DeliveryEvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping("/")
    public ResponseEntity<DeliveryEvaluation> createEvaluation(@RequestBody DeliveryEvaluation evaluation) {
        return ResponseEntity.ok(evaluationService.createEvaluation(evaluation));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryEvaluation> getEvaluation(@PathVariable Long id) {
        return ResponseEntity.ok(evaluationService.getEvaluationById(id));
    }

    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<List<DeliveryEvaluation>> getEvaluationsForVendor(@PathVariable Long vendorId) {
        return ResponseEntity.ok(evaluationService.getEvaluationsForVendor(vendorId));
    }

    @GetMapping("/requirement/{reqId}")
    public ResponseEntity<List<DeliveryEvaluation>> getEvaluationsForRequirement(@PathVariable Long reqId) {
        return ResponseEntity.ok(evaluationService.getEvaluationsForRequirement(reqId));
    }
}
