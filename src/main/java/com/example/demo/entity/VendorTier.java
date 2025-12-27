package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class VendorTier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tierName;

    // getters and setters
}
