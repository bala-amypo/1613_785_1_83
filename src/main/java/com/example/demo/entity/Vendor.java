package com.example.demo.entity;


import jakarta.persistence.*;
import java.sql.Timestamp;


@Entity
public class Vendor {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


@Column(unique = true, nullable = false)
private String name;


private String contactEmail;
private String contactPhone;
private Boolean active = true;
private Timestamp createdAt = new Timestamp(System.currentTimeMillis());
private Timestamp updatedAt = new Timestamp(System.currentTimeMillis());


// getters and setters
}