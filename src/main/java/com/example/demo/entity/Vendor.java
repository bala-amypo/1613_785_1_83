package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

@Data
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

    private Timestamp createdAt;
    private Timestamp updatedAt;
}
