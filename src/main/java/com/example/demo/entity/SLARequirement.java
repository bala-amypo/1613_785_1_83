package com.example.demo.entity;


import jakarta.persistence.*;


@Entity
public class SLARequirement {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


@Column(unique = true)
private String requirementName;


private String description;
private Integer maxDeliveryDays;
private Double minQualityScore;
private Boolean active = true;
// getters and setters
}