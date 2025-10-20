package com.example.demo1.dto;

import java.util.List;
import java.util.UUID;

public class JobDTO {
    private String jobName;
    private String jobCode;
    private String transactionType;
    private String jobHours;
    private Double costPerHour;
    private Double costIncurred;
    private Integer jobCount;
    private List<UUID> assignedUserIds; // just the IDs of assigned users

    // --- Getters & Setters ---
    public String getJobName() { return jobName; }
    public void setJobName(String jobName) { this.jobName = jobName; }

    public String getJobCode() { return jobCode; }
    public void setJobCode(String jobCode) { this.jobCode = jobCode; }

    public String getTransactionType() { return transactionType; }
    public void setTransactionType(String transactionType) { this.transactionType = transactionType; }

    public String  getJobHours() { return jobHours; }
    public void setJobHours(String jobHours) { this.jobHours = jobHours; }

    public Double getCostPerHour() { return costPerHour; }
    public void setCostPerHour(Double costPerHour) { this.costPerHour = costPerHour; }

    public Double getCostIncurred() { return costIncurred; }
    public void setCostIncurred(Double costIncurred) { this.costIncurred = costIncurred; }

    public Integer getJobCount() { return jobCount; }
    public void setJobCount(Integer jobCount) { this.jobCount = jobCount; }

    public List<UUID> getAssignedUserIds() { return assignedUserIds; }
    public void setAssignedUserIds(List<UUID> assignedUserIds) { this.assignedUserIds = assignedUserIds; }
}
