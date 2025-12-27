package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "vendor")
    private List<DeliveryEvaluation> evaluations;

    // getters and setters
}
