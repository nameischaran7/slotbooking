package com.cvr.sbook.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "slots")
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    private boolean isBooked = false;

    // Relationship with the Vendor (Turf/Shop)
    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    // FIX: Proper relationship with the User who booked the slot
    @ManyToOne
    @JoinColumn(name = "booked_by_user_id")
    private User bookedByUser;

    // Standard Constructor
    public Slot() {}

    // --- GETTERS AND SETTERS ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }

    public boolean isBooked() { return isBooked; }
    public void setBooked(boolean booked) { isBooked = booked; }

    public Vendor getVendor() { return vendor; }
    public void setVendor(Vendor vendor) { this.vendor = vendor; }

    public User getBookedByUser() { return bookedByUser; }
    public void setBookedByUser(User bookedByUser) { this.bookedByUser = bookedByUser; }
}