package com.example.demo1.controller;

import com.example.demo1.entity.User;
import jakarta.validation.Valid;
import com.example.demo1.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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


    // POST single user
    @PostMapping("/add-user")
    public ResponseEntity<String> addUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("User added successfully with id: " + savedUser.getId());
    }


    // Optional: Add multiple users at once
    @PostMapping("/add-users")
    public List<User> addUsers( @Valid @RequestBody List<User> users) {
        return userRepository.saveAll(users);

    }
    // PUT — update a user by ID
    @PutMapping("/update-user/{id}")
    public User updateUser(@Valid @PathVariable Long id, @RequestBody User newUserData) {
        Optional<User> existingUser = userRepository.findById(id);

        // Replace this:
        // if (existingUser.isEmpty()) {
        //     throw new RuntimeException("User not found with id " + id);
        // }

        // With this:
        if (existingUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + id);
        }

        User user = existingUser.get();
        user.setName(newUserData.getName());
        user.setRole(newUserData.getRole());
        return userRepository.save(user);
    }

    // ✅ DELETE — delete a user by ID
    @DeleteMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "User with id " + id + " deleted successfully.";
        } else {
            return "User not found with id " + id;
        }
    }




}
