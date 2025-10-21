package com.example.demo1.controller;

import com.example.demo1.entity.AssignedUser;
import com.example.demo1.service.AssignedUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080"})
@RestController
@RequestMapping("/assigned-users")
public class AssignedUserController {

    private final AssignedUserService assignedUserService;

    public AssignedUserController(AssignedUserService assignedUserService) {
        this.assignedUserService = assignedUserService;
    }

    @GetMapping("/get")
    public List<AssignedUser> getAllAssignedUsers() {
        return assignedUserService.getAllAssignedUser();
    }

    @GetMapping("/{id}")
    public AssignedUser getAssignedUser(@PathVariable UUID id) {
        return assignedUserService.getAssignedUserById(id);
    }

    @PostMapping
    public AssignedUser createAssignedUser(@RequestBody AssignedUser user) {
        return assignedUserService.createAssignedUser(user);
    }

    @PostMapping("/multiple")
    public List<AssignedUser> createMultipleAssignedUsers(@RequestBody List<AssignedUser> users) {
        return assignedUserService.createMultipleAssignedUsers(users);
    }

    @PutMapping("/{id}")
    public AssignedUser updateAssignedUser(@PathVariable UUID id, @RequestBody AssignedUser user) {
        return assignedUserService.updateAssignedUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteAssignedUser(@PathVariable UUID id) {
        assignedUserService.deleteAssignedUser(id);
    }
}
