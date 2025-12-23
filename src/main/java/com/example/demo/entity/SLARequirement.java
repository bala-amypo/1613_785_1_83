package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class SLARequirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String requirementName;

    private String description;
    private Integer maxDeliveryDays;
    private Integer minQualityScore;
    private Boolean active = true;

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public String getRequirementName() {
        return requirementName;
    }

    public String getDescription() {
        return description;
    }

    public Integer getMaxDeliveryDays() {
        return maxDeliveryDays;
    }

    public Integer getMinQualityScore() {
        return minQualityScore;
    }

    public Boolean getActive() {
        return active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRequirementName(String requirementName) {
        this.requirementName = requirementName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMaxDeliveryDays(Integer maxDeliveryDays) {
        this.maxDeliveryDays = maxDeliveryDays;
    }

    public void setMinQualityScore(Integer minQualityScore) {
        this.minQualityScore = minQualityScore;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
