package com.example.demo1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "job_id", updatable = false, nullable = false)
    private UUID id;

    private String jobName;
    private String description;
    private String status;

    @ManyToOne
    @JoinColumn(name = "phase_id", nullable = false)
    @JsonBackReference

    private Phase phase;

    @ManyToMany
    @JoinTable(
            name = "job_assigned_users",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")

    )
    private Set<User> assignedUsers = new HashSet<>();


    // Getters and setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getJobName() { return jobName; }
    public void setJobName(String jobName) { this.jobName = jobName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Phase getPhase() { return phase; }
    public void setPhase(Phase phase) { this.phase = phase; }

    public Set<User> getAssignedUsers() {   return assignedUsers;}

    public void setAssignedUsers(Set<User> assignedUsers) { this.assignedUsers = assignedUsers;}

}
