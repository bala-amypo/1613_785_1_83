package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class VendorTier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String tierName;

    @Column(nullable = false)
    private double minScoreThreshold;

    public VendorTier() {
    }

    public VendorTier(String tierName, double minScoreThreshold) {
        this.tierName = tierName;
        this.minScoreThreshold = minScoreThreshold;
    }

    public Long getId() {
        return id;
    }

    public String getTierName() {
        return tierName;
    }

    public void setTierName(String tierName) {
        this.tierName = tierName;
    }

    public double getMinScoreThreshold() {
        return minScoreThreshold;
    }

    public void setMinScoreThreshold(double minScoreThreshold) {
        this.minScoreThreshold = minScoreThreshold;
    }
}
