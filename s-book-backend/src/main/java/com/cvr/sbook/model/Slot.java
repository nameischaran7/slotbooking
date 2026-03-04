package com.cvr.sbook.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "slots")
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isBooked = false;
    // In your Spring Boot Slot.java
    private String bookedByName; // Store the name of the user who booked
    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    // Standard Constructors
    public Slot() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public String getBookedByName() {
        return bookedByName;
    }

    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public boolean isBooked() { return isBooked; }
    public void setBooked(boolean booked) { isBooked = booked; }
    public void setBookedByName(String bookedByName) { this.bookedByName = bookedByName; }
    public void setVendor(Vendor vendor) { this.vendor = vendor; }
}