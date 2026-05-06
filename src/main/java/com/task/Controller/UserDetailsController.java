package com.task.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.Model.UserDetails;
import com.task.repository.UserRepository;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserDetailsController {

    @Autowired
    private UserRepository repository;

    // ================= REGISTER =================
    @PostMapping("/register")
    public String register(@RequestBody UserDetails user) {

        // Check mobile number
        if (user.getMobileNumber() == null) {
            return "Mobile number is required!";
        }

        // Check email already exists
        Optional<UserDetails> existingUser =
                Optional.empty();

        if (existingUser.isPresent()) {
            return "Email already registered!";
        }

        repository.save(user);
        return "User Registered Successfully!";
    }

    // ================= LOGIN =================
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody UserDetails user) {

        Optional<UserDetails> existingUser =
                repository.findByEmailId(user.getEmailId());

        Map<String, String> response = new HashMap<>();

        if (existingUser.isEmpty()) {
            response.put("status", "error");
            response.put("message", "User not found!");
            return response;
        }

        UserDetails dbUser = existingUser.get();

        if (!dbUser.getPassword().equals(user.getPassword())) {
            response.put("status", "error");
            response.put("message", "Invalid Password!");
            return response;
        }

        response.put("status", "success");
        response.put("message", "Login Successful!");
        return response;
    }
    }