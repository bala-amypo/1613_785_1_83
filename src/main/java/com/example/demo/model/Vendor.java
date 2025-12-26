package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Vendor {

    @Id
    private Long id;
    private String name;

    // getters & setters
}
