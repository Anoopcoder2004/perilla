package com.example.demo1.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue
    private UUID jobId;

    private String jobCode;
    private String jobName;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    // 🆕 Additional fields required by JobService
    private String transactionType;   // 🆕 Type of transaction (e.g., Regular, Overtime)
    private String jobHours;           // 🆕 Hours worked (e.g., "09:02")
    private double costPerHour;        // 🆕 Cost rate per hour
    private double costIncurred;       // 🆕 Total cost for this job
    private double jobCount;           // 🆕 Job count or number of tasks done

    @ManyToOne
    @JoinColumn(name = "phase_id")
    private Phase phase;

    // 🆕 Rename "User" to "AssignedUser" to match your actual entity name
// Job.java
    @ManyToMany
    @JoinTable(
            name = "job_users",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonManagedReference
    private List<AssignedUser> assignedUser;

    // --- Getters & Setters ---
    public UUID getJobId() { return jobId; }
    public void setJobId(UUID jobId) { this.jobId = jobId; }

    public String getJobCode() { return jobCode; }
    public void setJobCode(String jobCode) { this.jobCode = jobCode; }

    public String getJobName() { return jobName; }
    public void setJobName(String jobName) { this.jobName = jobName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public Phase getPhase() { return phase; }
    public void setPhase(Phase phase) { this.phase = phase; }

    public List<AssignedUser> getAssignedUser() { return assignedUser; } // 🆕
    public void setAssignedUser(List<AssignedUser> assignedUser) { this.assignedUser = assignedUser; } // 🆕

    // 🆕 Newly added getters & setters
    public String getTransactionType() { return transactionType; }
    public void setTransactionType(String transactionType) { this.transactionType = transactionType; }

    public String getJobHours() { return jobHours; }
    public void setJobHours(String jobHours) { this.jobHours = jobHours; }

    public double getCostPerHour() { return costPerHour; }
    public void setCostPerHour(double costPerHour) { this.costPerHour = costPerHour; }

    public double getCostIncurred() { return costIncurred; }
    public void setCostIncurred(double costIncurred) { this.costIncurred = costIncurred; }

    public double getJobCount() { return jobCount; }
    public void setJobCount(double jobCount) { this.jobCount = jobCount; }
}
