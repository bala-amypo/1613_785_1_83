package com.example.demo.controller;

import com.example.demo.entity.DeliveryEvaluation;
import com.example.demo.service.DeliveryEvaluationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluations")
@Tag(name = "Delivery Evaluations")
public class DeliveryEvaluationController {

    private final DeliveryEvaluationService service;

    public DeliveryEvaluationController(DeliveryEvaluationService service) {
        this.service = service;
    }

    @PostMapping
    public DeliveryEvaluation create(@RequestBody DeliveryEvaluation evaluation) {
        return service.createEvaluation(evaluation);
    }

    @GetMapping("/{id}")
    public DeliveryEvaluation get(@PathVariable Long id) {
        return service.getEvaluationById(id);
    }

    @GetMapping("/vendor/{vendorId}")
    public List<DeliveryEvaluation> byVendor(@PathVariable Long vendorId) {
        return service.getEvaluationsForVendor(vendorId);
    }

    @GetMapping("/requirement/{reqId}")
    public List<DeliveryEvaluation> byRequirement(@PathVariable Long reqId) {
        return service.getEvaluationsForRequirement(reqId);
    }
}
