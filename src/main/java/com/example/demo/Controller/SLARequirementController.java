package com.example.demo.controller;

import com.example.demo.entity.SLARequirement;
import com.example.demo.service.SLARequirementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sla")
public class SLARequirementController {

    private final SLARequirementService service;

    public SLARequirementController(SLARequirementService service) {
        this.service = service;
    }

    @GetMapping
    public List<SLARequirement> getAll() {
        return service.getAllSLARequirements();
    }

    @PostMapping
    public SLARequirement create(@RequestBody SLARequirement sla) {
        return service.createSLARequirement(sla);
    }
}
