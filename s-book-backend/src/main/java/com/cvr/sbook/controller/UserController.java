package com.cvr.sbook.controller;

import com.cvr.sbook.model.User;
import com.cvr.sbook.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.cvr.sbook.model.User;
import com.cvr.sbook.repository.UserRepository; // Import it!
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository; // This line fixes the 'Cannot resolve symbol' error

    @PostMapping("/login")

    public ResponseEntity<?> login(@RequestBody User loginUser) { // Use <?> here
        return userRepository.findByEmail(loginUser.getEmail())
                .map(user -> {
                    if (user.getPassword().equals(loginUser.getPassword())) {
                        return ResponseEntity.ok(user); // Returns User object
                    } else {
                        return ResponseEntity.status(401).body("Invalid password, mowa!"); // Returns String
                    }
                })
                .orElse(ResponseEntity.status(404).body("User not found!")); // Returns String
    }

    @PostMapping("/signup")
    public User signup(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }
}
