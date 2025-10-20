package com.example.demo1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class AssignedUser {

    @Id
    @GeneratedValue
    private UUID userId;

    private String name;
    private String email;

    // AssignedUser.java
    @ManyToMany(mappedBy = "assignedUser") // must match Job's field name
    @JsonBackReference
    private List<Job> assignedJobs;

    // --- Getters & Setters ---
    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Job> getAssignedJobs() { return assignedJobs; }
    public void setAssignedJobs(List<Job> assignedJobs) { this.assignedJobs = assignedJobs; }
}
