package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sla_requirements")
public class SLARequirement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(name = "requirement_name", unique = true, nullable = false) private String requirementName;
    private String description;
    @Column(name = "max_delivery_days") private Integer maxDeliveryDays;
    @Column(name = "min_quality_score") private Double minQualityScore;
    private boolean active = true;

    public SLARequirement() {}
    public SLARequirement(String requirementName, String description, Integer maxDeliveryDays, Double minQualityScore) {
        this.requirementName = requirementName; this.description = description;
        this.maxDeliveryDays = maxDeliveryDays; this.minQualityScore = minQualityScore;
    }

    // Getters/Setters
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getRequirementName() { return requirementName; } public void setRequirementName(String requirementName) { this.requirementName = requirementName; }
    public String getDescription() { return description; } public void setDescription(String description) { this.description = description; }
    public Integer getMaxDeliveryDays() { return maxDeliveryDays; } public void setMaxDeliveryDays(Integer maxDeliveryDays) { this.maxDeliveryDays = maxDeliveryDays; }
    public Double getMinQualityScore() { return minQualityScore; } public void setMinQualityScore(Double minQualityScore) { this.minQualityScore = minQualityScore; }
    public boolean getActive() { return active; } public void setActive(boolean active) { this.active = active; }
}
