package com.example.demo.model;

public class VendorTier {
    private Long id;
    private String tierName;
    private Double minScoreThreshold;
    private String description;
    private Boolean active = true;

    public VendorTier() {}

    public VendorTier(String name, Double threshold, String desc) {
        this.tierName = name;
        this.minScoreThreshold = threshold;
        this.description = desc;
    }

    public void setId(Long id) { this.id = id; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
