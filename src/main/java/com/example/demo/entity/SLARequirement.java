package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class SLARequirement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int onTimeScore;
    private int qualityScore;

    // getters and setters
    public int getQualityScore() { return qualityScore; }
}
