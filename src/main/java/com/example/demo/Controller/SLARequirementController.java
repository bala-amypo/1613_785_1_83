package com.example.demo.controller;

import com.example.demo.entity.SLARequirement;
import com.example.demo.service.SLARequirementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sla-requirements")
public class SLARequirementController {

    private final SLARequirementService slaRequirementService;

    public SLARequirementController(SLARequirementService slaRequirementService) {
        this.slaRequirementService = slaRequirementService;
    }

    @PostMapping("/")
    public ResponseEntity<SLARequirement> createRequirement(@RequestBody SLARequirement req) {
        return ResponseEntity.ok(slaRequirementService.createRequirement(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SLARequirement> updateRequirement(@PathVariable Long id, @RequestBody SLARequirement req) {
        return ResponseEntity.ok(slaRequirementService.updateRequirement(id, req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SLARequirement> getRequirement(@PathVariable Long id) {
        return ResponseEntity.ok(slaRequirementService.getRequirementById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<SLARequirement>> getAllRequirements() {
        return ResponseEntity.ok(slaRequirementService.getAllRequirements());
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivateRequirement(@PathVariable Long id) {
        slaRequirementService.deactivateRequirement(id);
        return ResponseEntity.ok("SLA Requirement deactivated");
    }
}
