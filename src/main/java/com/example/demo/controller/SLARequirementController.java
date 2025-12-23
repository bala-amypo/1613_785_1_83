package com.example.demo.controller;

import com.example.demo.entity.SLARequirement;
import com.example.demo.service.SLARequirementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sla-requirements")
@Tag(name = "SLA Requirements", description = "Manage SLA requirements")
public class SLARequirementController {

    private final SLARequirementService slaService;

    public SLARequirementController(SLARequirementService slaService) {
        this.slaService = slaService;
    }

    @PostMapping
    public SLARequirement createRequirement(@RequestBody SLARequirement req) {
        return slaService.createRequirement(req);
    }

    @GetMapping("/{id}")
    public SLARequirement getRequirement(@PathVariable Long id) {
        return slaService.getRequirementById(id);
    }

    @GetMapping
    public List<SLARequirement> getAllRequirements() {
        return slaService.getAllRequirements();
    }

    @PutMapping("/{id}/deactivate")
    public SLARequirement deactivateRequirement(@PathVariable Long id) {
        return slaService.deactivateRequirement(id);
    }
}
