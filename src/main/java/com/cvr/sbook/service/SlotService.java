package com.cvr.sbook.service;

import com.cvr.sbook.model.Slot;
import com.cvr.sbook.model.Vendor;
import com.cvr.sbook.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class SlotService {

    @Autowired
    private SlotRepository slotRepository;

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
}