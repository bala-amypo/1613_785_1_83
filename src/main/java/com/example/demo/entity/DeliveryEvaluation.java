package com.example.demo.model;

import java.time.LocalDate;

public class DeliveryEvaluation {

    private Long id;
    private Vendor vendor;
    private SLARequirement slaRequirement;
    private Integer actualDeliveryDays;
    private Double qualityScore;
    private Boolean meetsDeliveryTarget;
    private Boolean meetsQualityTarget;
    private LocalDate evaluationDate;

    public DeliveryEvaluation() {}

    public DeliveryEvaluation(Vendor v, SLARequirement s, int days, double score, LocalDate date) {
        this.vendor = v;
        this.slaRequirement = s;
        this.actualDeliveryDays = days;
        this.qualityScore = score;
        this.evaluationDate = date;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Vendor getVendor() { return vendor; }
    public void setVendor(Vendor vendor) { this.vendor = vendor; }

    public SLARequirement getSlaRequirement() { return slaRequirement; }
    public void setSlaRequirement(SLARequirement slaRequirement) { this.slaRequirement = slaRequirement; }

    public Integer getActualDeliveryDays() { return actualDeliveryDays; }
    public void setActualDeliveryDays(Integer actualDeliveryDays) { this.actualDeliveryDays = actualDeliveryDays; }

    public Double getQualityScore() { return qualityScore; }
    public void setQualityScore(Double qualityScore) { this.qualityScore = qualityScore; }

    public Boolean getMeetsDeliveryTarget() { return meetsDeliveryTarget; }
    public void setMeetsDeliveryTarget(Boolean meetsDeliveryTarget) { this.meetsDeliveryTarget = meetsDeliveryTarget; }

    public Boolean getMeetsQualityTarget() { return meetsQualityTarget; }
    public void setMeetsQualityTarget(Boolean meetsQualityTarget) { this.meetsQualityTarget = meetsQualityTarget; }

    public LocalDate getEvaluationDate() { return evaluationDate; }
    public void setEvaluationDate(LocalDate evaluationDate) { this.evaluationDate = evaluationDate; }
}
