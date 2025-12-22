package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "tierName")
})
public class VendorTier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tierName;
    private Double minScoreThreshold;
    private String description;
    private Boolean active = true;

    // getters & setters
}
