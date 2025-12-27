package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class SLARequirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String requirementName;
    private String description;
    private Integer maxDeliveryDays;
    private Double qualityScoreThreshold;
    private Boolean active = true;

    public SLARequirement() {}

    public SLARequirement(String name, String desc, Integer days, Double quality) {
        this.requirementName = name;
        this.description = desc;
        this.maxDeliveryDays = days;
        this.qualityScoreThreshold = quality;
        this.active = true;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRequirementName() { return requirementName; }
    public void setRequirementName(String requirementName) { this.requirementName = requirementName; }

    public String getDescription() { return description; }

    public Integer getMaxDeliveryDays() { return maxDeliveryDays; }
    public Double getQualityScoreThreshold() { return qualityScoreThreshold; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
