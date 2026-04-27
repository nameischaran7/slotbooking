package com.cvr.sbook.controller;
import com.cvr.sbook.model.Slot;
import com.cvr.sbook.model.User;
import com.cvr.sbook.repository.SlotRepository;
import com.cvr.sbook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @PostMapping("/{slotId}/verify-checkin")
    public ResponseEntity<?> verifyAndCheckIn(@PathVariable Long slotId) {
        return slotRepository.findById(slotId).map(slot -> {
            if (!slot.isBooked()) {
                return ResponseEntity.badRequest().body("This slot was never booked, mowa!");
            }

            // Update the status to 'false' so it becomes available again
            // OR you can add a 'status' String field like "COMPLETED"
            slot.setBooked(false);
          slot.setBookedByUser(null);// Clear the name
            slotRepository.save(slot);

            return ResponseEntity.ok("Check-in Successful for slot: " + slotId);
        }).orElse(ResponseEntity.status(404).body("Invalid Slot ID"));
    }
}