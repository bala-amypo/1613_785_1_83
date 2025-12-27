package com.example.demo.controller;

import com.example.demo.model.SLARequirement;
import com.example.demo.service.SLARequirementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sla-requirements")
public class SLARequirementController {

    private final SLARequirementService service;

    public SLARequirementController(SLARequirementService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SLARequirement> create(@RequestBody SLARequirement req) {
        return ResponseEntity.ok(service.createRequirement(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SLARequirement> update(
            @PathVariable Long id,
            @RequestBody SLARequirement req) {
        return ResponseEntity.ok(service.updateRequirement(id, req));
    }

    @GetMapping
    public ResponseEntity<List<SLARequirement>> getAll() {
        return ResponseEntity.ok(service.getAllRequirements());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        service.deactivateRequirement(id);
        return ResponseEntity.noContent().build();
    }
}
