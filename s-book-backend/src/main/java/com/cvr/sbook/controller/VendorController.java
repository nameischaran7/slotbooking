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
    @Autowired
    private SlotService slotService;
    @GetMapping
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }
    // This handles the main Vendor Signup and Slot Generation
    @PostMapping("/signup")
    public Vendor signupVendor(@RequestBody Vendor vendor) {
        Vendor savedVendor = vendorRepository.save(vendor);
        // This ensures the 3rd tier (Database) is populated immediately
        slotService.generateDailySlots(savedVendor);
        return savedVendor;
    }
    @GetMapping("/test")
    public String test() {
        return "Controller is working, mowa!";
    }
    // Manual slot generation if something goes wrong
    @PostMapping("/generate/{vendorId}")
    public ResponseEntity<String> createSlots(@PathVariable Long vendorId) {
        return vendorRepository.findById(vendorId)
                .map(vendor -> {
                    slotService.generateDailySlots(vendor);
                    return ResponseEntity.ok("12 Slots created for " + vendor.getName());
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/search")
    public List<Vendor> searchVendors(@RequestParam String query) {
        // We pass the same 'query' string three times to search all three fields
        return vendorRepository.findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCaseOrLocationContainingIgnoreCase(
                query, query, query, Double.valueOf(query)
        );
    }
}