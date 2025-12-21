package com.example.demo.entity;

import jakarta.persistence.*;

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

    public void setVendor(Vendor vendor) { this.vendor = vendor; }
    public void setOnTimePercentage(Double v) { this.onTimePercentage = v; }
    public void setQualityCompliancePercentage(Double v) { this.qualityCompliancePercentage = v; }
    public void setOverallScore(Double v) { this.overallScore = v; }
}
