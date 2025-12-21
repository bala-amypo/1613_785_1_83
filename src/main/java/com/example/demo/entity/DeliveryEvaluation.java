package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class DeliveryEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Vendor vendor;

    @ManyToOne
    private SLARequirement slaRequirement;

    private Integer actualDeliveryDays;
    private Double qualityScore;

    private Boolean meetsDeliveryTarget;
    private Boolean meetsQualityTarget;

    public Vendor getVendor() { return vendor; }
    public SLARequirement getSlaRequirement() { return slaRequirement; }
    public Integer getActualDeliveryDays() { return actualDeliveryDays; }
    public Double getQualityScore() { return qualityScore; }

    public void setMeetsDeliveryTarget(Boolean v) { this.meetsDeliveryTarget = v; }
    public void setMeetsQualityTarget(Boolean v) { this.meetsQualityTarget = v; }
    public Boolean getMeetsDeliveryTarget() { return meetsDeliveryTarget; }
    public Boolean getMeetsQualityTarget() { return meetsQualityTarget; }
}
