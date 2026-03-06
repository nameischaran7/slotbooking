package com.cvr.sbook.controller;

import com.cvr.sbook.model.Vendor;
import com.cvr.sbook.repository.VendorRepository;
import com.cvr.sbook.service.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")





public class VendorController {

    @Autowired
    private VendorRepository vendorRepository;

    @GetMapping
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    @Autowired
    private SlotService slotService; // Add this line at the top with other @Autowired

    @PostMapping
    public Vendor addVendor(@RequestBody Vendor vendor) {
        Vendor savedVendor = vendorRepository.save(vendor);
        // Automatically create 12 slots for this new vendor!
        slotService.generateDailySlots(savedVendor);
        return savedVendor;
    }

    @GetMapping("/test")
    public String test() {
        return "Controller is working, mowa!";
    }

    @PostMapping("/generate/{vendorId}")
    public ResponseEntity<String> createSlots(@PathVariable Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId).get();
        slotService.generateDailySlots(vendor);
        return ResponseEntity.ok("12 Slots created for " + vendor.getName());
    }

    @PostMapping("/signup") // Add this specific sub-path
    public Vendor signupVendor(@RequestBody Vendor vendor) {
        return vendorRepository.save(vendor);
    }
}