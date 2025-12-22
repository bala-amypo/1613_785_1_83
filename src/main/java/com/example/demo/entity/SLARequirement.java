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
    private Boolean active = true;

    public Integer getMaxDeliveryDays() { return maxDeliveryDays; }
    public Double getMinQualityScore() { return minQualityScore; }
}
