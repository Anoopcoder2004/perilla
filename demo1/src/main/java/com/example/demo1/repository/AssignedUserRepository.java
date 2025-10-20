package com.example.demo1.repository;

import com.example.demo1.entity.AssignedUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssignedUserRepository extends JpaRepository<AssignedUser, UUID> {
    // You can add custom queries if needed
}
