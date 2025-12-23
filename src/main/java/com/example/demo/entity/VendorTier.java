package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class VendorTier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tierName;
    private int minScoreThreshold;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTierName() { return tierName; }
    public void setTierName(String tierName) { this.tierName = tierName; }

    public int getMinScoreThreshold() { return minScoreThreshold; }
    public void setMinScoreThreshold(int minScoreThreshold) {
        this.minScoreThreshold = minScoreThreshold;
    }
}
