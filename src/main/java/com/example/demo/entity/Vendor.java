package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean active;

    @ManyToOne
    private VendorTier tier;

    // getters and setters
}
