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

    // ADD THESE TWO FIELDS FOR LOGIN
    @Column(unique = true)
    private String email;
    private String password;

    // Add Getters and Setters for these two (Alt + Insert)
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
}