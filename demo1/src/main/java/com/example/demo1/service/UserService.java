package com.example.demo1.service;

import com.example.demo1.entity.User;
import com.example.demo1.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create a new user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get user by ID
    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    // Update user
    public User updateUser(UUID id, User updatedUser) {
        User existingUser = getUserById(id);
        existingUser.setName(updatedUser.getName()); // or other fields
        existingUser.setEmail(updatedUser.getEmail()); // if you have email
        // Add other fields as needed
        return userRepository.save(existingUser);
    }

    // Delete user
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    // Find multiple users by IDs (used for assigning users to jobs)
    public Set<User> findUsersByIds(Set<UUID> userIds) {
        return new HashSet<>(userRepository.findAllById(userIds));
    }
}
