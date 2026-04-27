package com.cvr.sbook.service;

import com.cvr.sbook.model.Slot;
import com.cvr.sbook.model.User;
import com.cvr.sbook.model.Vendor;
import com.cvr.sbook.repository.SlotRepository;
import com.cvr.sbook.repository.VendorRepository; // Add this
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled; // Add this
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class SlotService {

    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private VendorRepository vendorRepository;

    /**
     * CORE BOOKING LOGIC
     * Using @Transactional ensures Atomic operations (Concurrency Prevention)
     */
    @Transactional
    public Slot bookSlot(Long slotId, User user) {
        Slot slot = slotRepository.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        if (slot.isBooked()) {
            throw new RuntimeException("Slot already booked, mowa!");
        }

        slot.setBooked(true);
        slot.setBookedByUser(user);

        return slotRepository.save(slot);
    }

    /**
     * AUTOMATED REFRESH LOGIC
     * Runs every day at midnight (Cron: 0 0 0 * * *)
     */
    @Scheduled(cron = "0 10 0 * * *")
    @Transactional
    public void autoRefreshSlots() {
        // 1. Clear old bookings for a fresh start
        slotRepository.deleteAll();

        // 2. Re-generate slots for all registered vendors
        List<Vendor> vendors = vendorRepository.findAll();
        for (Vendor vendor : vendors) {
            generateDailySlots(vendor);
        }
        System.out.println("Midnight Refresh: All slots re-generated for all vendors!");
    }

    public void generateDailySlots(Vendor vendor) {
        // Start from 9 AM today
        LocalDateTime startTime = LocalDateTime.now().with(LocalTime.of(9, 0, 0));

        for (int i = 0; i < 12; i++) {
            Slot slot = new Slot();
            // Storing as String if your model expects String, or LocalDateTime
            slot.setStartTime(LocalDateTime.parse(startTime.plusHours(i).toString()));
            slot.setVendor(vendor);
            slot.setBooked(false);
            slotRepository.save(slot);
        }
    }
}