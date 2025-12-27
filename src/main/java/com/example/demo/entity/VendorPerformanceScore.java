package com.example.demo.model;

import java.time.LocalDateTime;

public class VendorPerformanceScore {

    private Vendor vendor;
    private Double onTimePercentage;
    private Double qualityCompliancePercentage;
    private Double overallScore;
    private LocalDateTime calculatedAt = LocalDateTime.now();

    public VendorPerformanceScore(Vendor v, Double o, Double q, Double g) {
        this.vendor = v;
        this.onTimePercentage = o;
        this.qualityCompliancePercentage = q;
        this.overallScore = g;
    }

    public Double getOnTimePercentage() { return onTimePercentage; }
    public Double getQualityCompliancePercentage() { return qualityCompliancePercentage; }
    public Double getOverallScore() { return overallScore; }
    public LocalDateTime getCalculatedAt() { return calculatedAt; }
}
