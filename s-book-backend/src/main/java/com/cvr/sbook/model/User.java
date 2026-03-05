package com.cvr.sbook.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String name;
    private String role;
    public String getEmail(){
        return  this.email;
    }
    public String getPassword(){
        return this.password;
    }
    public String getRole(){return this.role;}
    public Long getId(){return this.id;}

    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    // Getters and Setters
    // Inside User.java
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setRole(String role) { this.role = role; }

}
