package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
public class Vendor {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    private String contactEmail;
    private String contactPhone;
    private Boolean active = true;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    // getters & setters
}
