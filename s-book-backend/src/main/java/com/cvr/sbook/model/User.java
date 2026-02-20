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
    public String getEmail(){
        return  this.email;
    }
    public String getPassword(){
        return this.password;
    }
    // Getters and Setters
}
