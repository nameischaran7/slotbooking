package com.cvr.sbook.service;

import com.cvr.sbook.model.Slot;
import com.cvr.sbook.model.Vendor;
import com.cvr.sbook.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class SlotService {

    @Autowired
    private SlotRepository slotRepository;
    @Transactional
    public String bookSlot(Long slotId, Long userId) {
        // 1. Lock the slot so no one else can touch it
        Slot slot = slotRepository.findByIdWithLock(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        // 2. Check if it's already booked
        if (slot.getIsBooked()) {
            return "ALREADY_BOOKED"; // This stops the 2nd person
        }

        // 3. Perform the booking
        slot.setBooked(true);
        slot.setUserId(userId);
        slotRepository.save(slot);

        return "SUCCESS";
    }
    public void generateDailySlots(Vendor vendor) {
        // Start from 9 AM today
        LocalDateTime startTime = LocalDateTime.now().with(LocalTime.of(9, 0, 0));

        for (int i = 0; i < 12; i++) { // Generate 12 slots (1 hour each)
            Slot slot = new Slot();
            slot.setStartTime(startTime.plusHours(i));
            slot.setVendor(vendor);
            slot.setBooked(false);
            slotRepository.save(slot);
        }

    }
    // Pass the userName as a parameter
    public Slot bookSlot(Long slotId, String userName) {
        Slot slot = slotRepository.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        if (slot.isBooked()) {
            throw new RuntimeException("Slot already booked!");
        }

        slot.setBooked(true);
        slot.setBookedByName(userName); // <--- THIS SAVES THE NAME TO THE DB!
        return slotRepository.save(slot);
    }
}