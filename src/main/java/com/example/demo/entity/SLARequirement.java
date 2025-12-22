package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class SLARequirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String requirementName;

    private Integer maxDeliveryDays;
    private Double minQualityScore;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRequirementName() { return requirementName; }
    public void setRequirementName(String requirementName) {
        this.requirementName = requirementName;
    }

    public Integer getMaxDeliveryDays() { return maxDeliveryDays; }
    public void setMaxDeliveryDays(Integer maxDeliveryDays) {
        this.maxDeliveryDays = maxDeliveryDays;
    }

    public Double getMinQualityScore() { return minQualityScore; }
    public void setMinQualityScore(Double minQualityScore) {
        this.minQualityScore = minQualityScore;
    }
}
