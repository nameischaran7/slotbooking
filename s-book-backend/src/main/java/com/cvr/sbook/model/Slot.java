package com.cvr.sbook.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "slots")
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
    public LocalDateTime getEndTime(){
        return endTime;
    }

    public String getBookedByName() {
        return bookedByName;
    }
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public boolean isBooked() { return isBooked; }
    public void setBooked(boolean booked) { isBooked = booked; }
    public void setBookedByName(String bookedByName) { this.bookedByName = bookedByName; }
    public void setVendor(Vendor vendor) { this.vendor = vendor; }

    public boolean getIsBooked() {
        return isBooked;
    }

    public void setUserId(Long userId) {
        id=userId;
    }
}