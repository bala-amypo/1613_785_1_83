package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class VendorTier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String tierName;

    private Double minScoreThreshold;
    private Boolean active = true;
}
