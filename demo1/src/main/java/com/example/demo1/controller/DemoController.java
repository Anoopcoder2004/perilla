package com.example.demo1.controller;

import com.example.demo1.entity.User;
import com.example.demo1.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DemoController {

    private final UserRepository userRepository;

    public DemoController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    // Get all users from the database
    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    // Add a single user
    @PostMapping("/add-user")
    public User addUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // Optional: Add multiple users at once
    @PostMapping("/add-users")
    public List<User> addUsers(@RequestBody List<User> users) {
        return userRepository.saveAll(users);
    }
}
