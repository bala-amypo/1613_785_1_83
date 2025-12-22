package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vendor_performance_score")
public class VendorPerformanceScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long vendor;

    @Column(nullable = false)
    private Double score;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public VendorPerformanceScore() {}

    public VendorPerformanceScore(Long vendor, Double score) {
        this.vendor = vendor;
        this.score = score;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public Long getVendor() { return vendor; }
    public void setVendor(Long vendor) { this.vendor = vendor; }
    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
