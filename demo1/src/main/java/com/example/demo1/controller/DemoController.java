package com.example.demo1.controller;

import com.example.demo1.entity.Department;
import com.example.demo1.entity.User;
import jakarta.validation.Valid;
import com.example.demo1.repository.UserRepository;
import com.example.demo1.repository.DepartmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private final DepartmentRepository departmentRepository;

    public DemoController(
            UserRepository userRepository,
            DepartmentRepository departmentRepository

    ) {
        this.userRepository = userRepository;
        this.departmentRepository=departmentRepository;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
    @GetMapping("/users")
    public Page<User> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        return userRepository.findAll(pageable);
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
        if (existingUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + id);
        }
        User user = existingUser.get();

        user.setName(newUserData.getName());
        user.setRole(newUserData.getRole());

        if (newUserData.getDepartment() != null && newUserData.getDepartment().getId() != null) {
            Department dept = departmentRepository.findById(newUserData.getDepartment().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found with id " + newUserData.getDepartment().getId()));
            user.setDepartment(dept);
        }
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
