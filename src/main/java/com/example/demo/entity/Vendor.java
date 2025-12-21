package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private Boolean active = true;

    public Long getId() { return id; }
    public String getName() { return name; }
    public Boolean getActive() { return active; }

    public void setName(String name) { this.name = name; }
    public void setActive(Boolean active) { this.active = active; }
}
