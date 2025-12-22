package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class VendorTier {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String tierName;

    private Double minScoreThreshold;
    private String description;
    private Boolean active = true;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTierName() { return tierName; }
    public void setTierName(String tierName) { this.tierName = tierName; }

    public Double getMinScoreThreshold() { return minScoreThreshold; }
    public void setMinScoreThreshold(Double minScoreThreshold) {
        this.minScoreThreshold = minScoreThreshold;
    }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
