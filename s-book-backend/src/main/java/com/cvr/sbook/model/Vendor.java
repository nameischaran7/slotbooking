package com.cvr.sbook.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vendors")
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private String location;
    private Double pricePerHour;

    // 1. Mandatory No-Args Constructor
    public Vendor() {}

    // 2. Standard Getters and Setters (IntelliJ can generate these: Alt+Insert)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Double getPricePerHour() { return pricePerHour; }
    public void setPricePerHour(Double pricePerHour) { this.pricePerHour = pricePerHour; }
}