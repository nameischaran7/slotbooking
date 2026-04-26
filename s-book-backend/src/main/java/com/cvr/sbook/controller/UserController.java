package com.cvr.sbook.controller;
import com.cvr.sbook.model.User;
import com.cvr.sbook.model.Vendor;
import com.cvr.sbook.repository.UserRepository;
import com.cvr.sbook.repository.VendorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.cvr.sbook.model.User;
import com.cvr.sbook.repository.UserRepository; // Import it!
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository; // This line fixes the 'Cannot resolve symbol' error
    @Autowired // MAKE SURE THIS IS HERE!
    private VendorRepository vendorRepository;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        // 1. Try finding in Users table first
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
        if (user.isPresent() && user.get().getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.ok(user.get());
        }

        // 2. If not found, try finding in Vendors table

        Optional<Vendor> vendor = vendorRepository.findByEmail(loginRequest.getEmail());
        if (vendor.isPresent() && vendor.get().getPassword().equals(loginRequest.getPassword())) {
            // Map Vendor data to a User object format so Android understands it
            User vendorUser = new User();
            vendorUser.setId(vendor.get().getId());
            vendorUser.setName(vendor.get().getName());
            vendorUser.setRole("VENDOR");
            vendorUser.setEmail(vendor.get().getEmail());
            return ResponseEntity.ok(vendorUser);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials, mowa!");
    }
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @PostMapping("/signup")
    public User signup(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }
}
