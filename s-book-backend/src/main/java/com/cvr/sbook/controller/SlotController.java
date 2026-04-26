package com.cvr.sbook.controller;
import com.cvr.sbook.model.Slot;
import com.cvr.sbook.model.User;
import com.cvr.sbook.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/slots")
public class SlotController {

    @Autowired
    private SlotRepository slotRepository;

    // Get all slots (useful for testing)
    @GetMapping
    public List<Slot> getAllSlots() {
        return slotRepository.findAll();
    }

    // Get available slots for a specific vendor
    // Example: http://localhost:8010/api/slots/vendor/1
    @GetMapping("/vendor/{vendorId}")
    public List<Slot> getSlotsByVendor(@PathVariable Long vendorId) {
        return slotRepository.findByVendorId(vendorId);
    }

    // Update a slot to 'booked' status

    @PostMapping("/{slotId}/book")
    public Slot bookSlot(@PathVariable Long slotId, @RequestBody User userRequest) {
        Slot slot = slotRepository.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        if (slot.isBooked()) {
            throw new RuntimeException("Slot is already booked, mowa!");
        }

        slot.setBooked(true);

        // FIX: Set the actual User object instead of just a name string
        slot.setBookedByUser(userRequest);

        return slotRepository.save(slot);
    }
    @GetMapping("/user/{userId}")
    public List<Slot> getUserBookings(@PathVariable Long userId) {
        return slotRepository.findByBookedByUserId(userId);
    }
}