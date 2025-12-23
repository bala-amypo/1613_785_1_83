package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
public class VendorPerformanceScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Vendor vendor;

    private double score;

    private Timestamp calculatedAt;

    // ✅ REQUIRED setters
    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setCalculatedAt(Timestamp calculatedAt) {
        this.calculatedAt = calculatedAt;
    }

    // getters optional but recommended
}
