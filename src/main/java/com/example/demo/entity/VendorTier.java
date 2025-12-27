package com.example.demo.model;

public class VendorTier {

    private Long id;
    private String tierName;
    private Double minScoreThreshold;
    private String description;
    private Boolean active = true;

    public VendorTier() {}

    public VendorTier(String name, Double score, String desc) {
        this.tierName = name;
        this.minScoreThreshold = score;
        this.description = desc;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTierName() { return tierName; }
    public Double getMinScoreThreshold() { return minScoreThreshold; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
