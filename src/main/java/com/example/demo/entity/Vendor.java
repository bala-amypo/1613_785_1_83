package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    private String contactEmail;
    private String contactPhone;
    private Boolean active = true;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Vendor() {}
    public Vendor(String name, String contactEmail, String contactPhone) {
        this.name = name;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.active = true;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }
    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
