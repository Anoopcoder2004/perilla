package com.example.demo1.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private UUID userId;

    private String name;
    private String email;

    @JsonIgnore   // prevent infinite recursion
    @ManyToMany(mappedBy = "assignedUsers")
    private Set<Job> jobs = new HashSet<>();

    // Getters and setters
    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Set<Job> getJobs() { return jobs; }
    public void setJobs(Set<Job> jobs) { this.jobs = jobs; }
}
