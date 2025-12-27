package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class VendorTier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tierName;
    private double minScoreThreshold;
    private String description;
    private Boolean active = true;

    public VendorTier() {}

    public VendorTier(String name, double score, String desc) {
        this.tierName = name;
        this.minScoreThreshold = score;
        this.description = desc;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTierName() { return tierName; }
    public double getMinScoreThreshold() { return minScoreThreshold; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
