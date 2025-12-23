package com.example.demo.controller;

import com.example.demo.entity.DeliveryEvaluation;
import com.example.demo.service.DeliveryEvaluationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluations")
@Tag(name = "Delivery Evaluations", description = "Manage delivery evaluations")
public class DeliveryEvaluationController {

    private final DeliveryEvaluationService evaluationService;

    public DeliveryEvaluationController(DeliveryEvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping
    public DeliveryEvaluation createEvaluation(@RequestBody DeliveryEvaluation evaluation) {
        return evaluationService.createEvaluation(evaluation);
    }

    @GetMapping("/{id}")
    public DeliveryEvaluation getEvaluation(@PathVariable Long id) {
        return evaluationService.getEvaluationById(id);
    }

    @GetMapping("/vendor/{vendorId}")
    public List<DeliveryEvaluation> getEvaluationsForVendor(@PathVariable Long vendorId) {
        return evaluationService.getEvaluationsForVendor(vendorId);
    }

    @GetMapping("/requirement/{reqId}")
    public List<DeliveryEvaluation> getEvaluationsForRequirement(@PathVariable Long reqId) {
        return evaluationService.getEvaluationsForRequirement(reqId);
    }
}
