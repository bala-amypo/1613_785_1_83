package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class VendorPerformanceScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Vendor vendor;

    private Double onTimePercentage;
    private Double qualityCompliancePercentage;
    private Double overallScore;
    private LocalDate calculatedAt = LocalDate.now();

    public VendorPerformanceScore() {}

    public VendorPerformanceScore(Vendor vendor, Double onTime, Double quality, Double overall) {
        this.vendor = vendor;
        this.onTimePercentage = onTime;
        this.qualityCompliancePercentage = quality;
        this.overallScore = overall;
        this.calculatedAt = LocalDate.now();
    }

    // Getters/Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Vendor getVendor() { return vendor; }
    public void setVendor(Vendor vendor) { this.vendor = vendor; }
    public Double getOnTimePercentage() { return onTimePercentage; }
    public void setOnTimePercentage(Double onTimePercentage) { this.onTimePercentage = onTimePercentage; }
    public Double getQualityCompliancePercentage() { return qualityCompliancePercentage; }
    public void setQualityCompliancePercentage(Double qualityCompliancePercentage) { this.qualityCompliancePercentage = qualityCompliancePercentage; }
    public Double getOverallScore() { return overallScore; }
    public void setOverallScore(Double overallScore) { this.overallScore = overallScore; }
    public LocalDate getCalculatedAt() { return calculatedAt; }
    public void setCalculatedAt(LocalDate calculatedAt) { this.calculatedAt = calculatedAt; }
}
