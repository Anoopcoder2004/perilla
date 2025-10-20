package com.example.demo1.service;

import com.example.demo1.dto.JobDTO;
import com.example.demo1.entity.AssignedUser;
import com.example.demo1.entity.Job;
import com.example.demo1.entity.Phase;
import com.example.demo1.repository.JobRepository;
import com.example.demo1.repository.PhaseRepository;
import com.example.demo1.repository.AssignedUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final PhaseRepository phaseRepository;
    private final AssignedUserRepository assignedUserRepository;

    public JobService(JobRepository jobRepository, PhaseRepository phaseRepository, AssignedUserRepository assignedUserRepository) {
        this.jobRepository = jobRepository;
        this.phaseRepository = phaseRepository;
        this.assignedUserRepository = assignedUserRepository;
    }

    // ✅ Get all jobs
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    // ✅ Get all jobs under a specific phase
    public List<Job> getJobsByPhase(UUID phaseId) {
        Phase phase = phaseRepository.findById(phaseId)
                .orElseThrow(() -> new RuntimeException("Phase not found with ID: " + phaseId));
        return jobRepository.findByPhase(phase);
    }
    // ✅ Get job by ID
    public Job getJobById(UUID jobId) {
        return jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found with ID: " + jobId));
    }
    // ✅ Create job and link it to a specific phase
    public Job createJob(UUID phaseId, Job job) {
        Phase phase = phaseRepository.findById(phaseId)
                .orElseThrow(() -> new RuntimeException("Phase not found with ID: " + phaseId));
        job.setPhase(phase);
        return jobRepository.save(job);
    }

    // ✅ Update an existing job
    // ✅ Update an existing job
    public Job updateJob(UUID jobId, Job updatedJob) {
        Job existingJob = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found with ID: " + jobId));

        existingJob.setJobCode(updatedJob.getJobCode());
        existingJob.setJobName(updatedJob.getJobName());
        existingJob.setTransactionType(updatedJob.getTransactionType()); // 🆕
        existingJob.setJobHours(updatedJob.getJobHours());               // 🆕
        existingJob.setCostPerHour(updatedJob.getCostPerHour());         // 🆕
        existingJob.setCostIncurred(updatedJob.getCostIncurred());       // 🆕
        existingJob.setJobCount(updatedJob.getJobCount());               // 🆕
        existingJob.setStartDate(updatedJob.getStartDate());
        existingJob.setEndDate(updatedJob.getEndDate());
        existingJob.setAssignedUser(updatedJob.getAssignedUser());       // 🆕 List<AssignedUser>

        return jobRepository.save(existingJob);
    }


    // ✅ Delete job by ID
    public void deleteJob(UUID jobId) {
        if (!jobRepository.existsById(jobId)) {
            throw new RuntimeException("Job not found with ID: " + jobId);
        }
        jobRepository.deleteById(jobId);
    }
    public Job createJobFromDTO(UUID phaseId, JobDTO jobDTO) {
        Phase phase = phaseRepository.findById(phaseId)
                .orElseThrow(() -> new RuntimeException("Phase not found"));

        Job job = new Job();
        job.setJobName(jobDTO.getJobName());
        job.setJobCode(jobDTO.getJobCode());
        job.setTransactionType(jobDTO.getTransactionType());
        job.setJobHours(jobDTO.getJobHours());
        job.setCostPerHour(jobDTO.getCostPerHour());
        job.setCostIncurred(jobDTO.getCostIncurred());
        job.setJobCount(jobDTO.getJobCount());
        job.setPhase(phase);

        // Set assigned users
        if (jobDTO.getAssignedUserIds() != null) {
            List<AssignedUser> users = assignedUserRepository.findAllById(jobDTO.getAssignedUserIds());
            job.setAssignedUser(users);
        }

        return jobRepository.save(job);
    }

}
