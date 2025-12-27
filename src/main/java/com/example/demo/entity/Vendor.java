package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String contactEmail;
    private String contactPhone;
    private Boolean active = true;

    public Vendor() {}

    public Vendor(String name, String email, String phone) {
        this.name = name;
        this.contactEmail = email;
        this.contactPhone = phone;
        this.active = true;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }

    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
