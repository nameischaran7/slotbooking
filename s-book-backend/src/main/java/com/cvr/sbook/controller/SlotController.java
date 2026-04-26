package com.cvr.sbook.controller;
import com.cvr.sbook.model.Slot;
import com.cvr.sbook.model.User;
import com.cvr.sbook.repository.SlotRepository;
import com.cvr.sbook.repository.UserRepository;
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
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/{slotId}/book")
    public Slot bookSlot(@PathVariable Long slotId, @RequestBody User userRequest) {
        Slot slot = slotRepository.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        if (slot.isBooked()) {
            throw new RuntimeException("Slot is already booked, mowa!");
        }

        // NEW: Fetch the full user details using the ID from the request
        // This ensures the 'name' is not null in the response!
        User fullUser = userRepository.findById(userRequest.getId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userRequest.getId()));

        slot.setBooked(true);
        slot.setBookedByUser(fullUser);

        return slotRepository.save(slot);
    }
    @GetMapping("/user/{userId}")
    public List<Slot> getUserBookings(@PathVariable Long userId) {
        return slotRepository.findByBookedByUserId(userId);
    }
    @PostMapping("/{slotId}/checkin")
    public Slot checkIn(@PathVariable Long slotId) {
        Slot slot = slotRepository.findById(slotId).orElseThrow();
        slot.setBooked(false); // This is what "unbooks" the slot
        slot.setBookedByUser(null);
        return slotRepository.save(slot);
    }
}