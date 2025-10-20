package com.example.demo1.service;

import com.example.demo1.entity.AssignedUser;
import com.example.demo1.repository.AssignedUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AssignedUserService {

    private final AssignedUserRepository assignedUserRepository;

    public AssignedUserService(AssignedUserRepository assignedUserRepository) {
        this.assignedUserRepository = assignedUserRepository;
    }

    public List<AssignedUser> getAllAssignedUser() {
        return assignedUserRepository.findAll();
    }

    public AssignedUser getAssignedUserById(UUID id) {
        return assignedUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assigned user not found"));
    }

    public AssignedUser createAssignedUser(AssignedUser user) {
        return assignedUserRepository.save(user);
    }

    public List<AssignedUser> createMultipleAssignedUsers(List<AssignedUser> users) {
        return assignedUserRepository.saveAll(users);
    }

    public AssignedUser updateAssignedUser(UUID id, AssignedUser updatedUser) {
        AssignedUser user = getAssignedUserById(id);

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());

        return assignedUserRepository.save(user);
    }

    public void deleteAssignedUser(UUID id) {
        assignedUserRepository.deleteById(id);
    }
}
