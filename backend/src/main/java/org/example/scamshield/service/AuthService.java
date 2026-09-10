package org.example.scamshield.service;

import org.example.scamshield.dto.RegisterRequest;
import org.example.scamshield.entity.User;
import org.example.scamshield.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.scamshield.dto.LoginRequest;
import org.example.scamshield.dto.LoginResponse;
import java.util.Optional;
import org.example.scamshield.dto.UserResponse;
import org.example.scamshield.dto.UpdateProfileRequest;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public String register(RegisterRequest request) {

        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already exists!";
        }

        // Create User object
        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // Later we'll encrypt it
        user.setRole("USER");

        // Save to database
        userRepository.save(user);

        return "User Registered Successfully";
    }
    public LoginResponse login(LoginRequest request) {

        System.out.println("Email received: " + request.getEmail());

        User user = userRepository.findByEmail(request.getEmail());
        System.out.println(user);
        System.out.println("DB Password: " + user.getPassword());
        System.out.println("Entered Password: " + request.getPassword());

        if (user == null) {
            return new LoginResponse("User not found!", null, null, null);
        }

        if (!user.getPassword().equals(request.getPassword())) {
            return new LoginResponse("Invalid Password!", null, null, null);
        }

        return new LoginResponse(
                "Login Successful",
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }
    public UserResponse getProfile(Long id) {

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            return null;
        }

        User user = optionalUser.get();

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }
    public String updateProfile(Long id, UpdateProfileRequest request) {

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            return "User not found!";
        }

        User user = optionalUser.get();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        userRepository.save(user);

        return "Profile Updated Successfully";
    }
}