package com.example.demo.controller;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.service.DeliveryEvaluationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evaluations")
public class DeliveryEvaluationController {

    private final DeliveryEvaluationService service;

    public DeliveryEvaluationController(DeliveryEvaluationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DeliveryEvaluation> create(@RequestBody DeliveryEvaluation eval) {
        return ResponseEntity.ok(service.createEvaluation(eval));
    }

    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<List<DeliveryEvaluation>> byVendor(@PathVariable Long vendorId) {
        return ResponseEntity.ok(service.getEvaluationsForVendor(vendorId));
    }

    @GetMapping("/sla/{slaId}")
    public ResponseEntity<List<DeliveryEvaluation>> bySla(@PathVariable Long slaId) {
        return ResponseEntity.ok(service.getEvaluationsForRequirement(slaId));
    }
}
