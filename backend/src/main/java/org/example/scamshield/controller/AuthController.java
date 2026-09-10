package org.example.scamshield.controller;

import org.example.scamshield.dto.LoginRequest;
import org.example.scamshield.dto.LoginResponse;
import org.example.scamshield.dto.RegisterRequest;
import org.example.scamshield.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.example.scamshield.dto.UserResponse;
import org.example.scamshield.dto.UpdateProfileRequest;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
    @GetMapping("/profile/{id}")
    public UserResponse getProfile(@PathVariable Long id) {
        return authService.getProfile(id);
    }
    @PutMapping("/profile/{id}")
    public String updateProfile(@PathVariable Long id,
                                @RequestBody UpdateProfileRequest request) {

        return authService.updateProfile(id, request);
    }
}