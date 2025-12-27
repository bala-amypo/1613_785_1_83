package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class DeliveryEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Vendor vendor;

    @ManyToOne
    private SLARequirement sla;

    private int deliveryScore;

    // getters and setters
}
