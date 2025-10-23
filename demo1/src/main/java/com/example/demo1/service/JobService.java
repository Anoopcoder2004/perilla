package com.example.demo1.service;

import com.example.demo1.entity.Job;
import com.example.demo1.entity.Phase;
import com.example.demo1.entity.User;
import com.example.demo1.repository.JobRepository;
import com.example.demo1.repository.PhaseRepository;
import com.example.demo1.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final PhaseRepository phaseRepository;
    private  final UserRepository userRepository;
    private final UserService userService;

    public JobService(JobRepository jobRepository, PhaseRepository phaseRepository, UserRepository userRepository, UserService userService) {
        this.jobRepository = jobRepository;
        this.phaseRepository = phaseRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public List<Job> getJobsByPhase(UUID id) {
        return jobRepository.findByPhaseId(id);
    }

    public Job createJob(UUID phaseId, Job job, Set<UUID> userIds) {
        Phase phase = phaseRepository.findById(phaseId)
                .orElseThrow(() -> new RuntimeException("Phase not found"));
        job.setPhase(phase);

        if (userIds != null && !userIds.isEmpty()) {
            Set<User> users = new HashSet<>(userRepository.findAllById(userIds));
            job.setAssignedUsers(users);
        }

        return jobRepository.save(job);
    }


    public Job getJobById(UUID phaseId, UUID jobId) {
        return jobRepository.findById(jobId)
                .filter(job -> job.getPhase().getId().equals(phaseId))
                .orElseThrow(() -> new RuntimeException("Job not found"));
    }

    public Job updateJob(UUID phaseId, UUID jobId, Job updatedJob) {
        Job existingJob = getJobById(phaseId, jobId);
        existingJob.setJobName(updatedJob.getJobName());
        existingJob.setDescription(updatedJob.getDescription());
        existingJob.setStatus(updatedJob.getStatus());
        return jobRepository.save(existingJob);
    }

    public void deleteJob(UUID phaseId, UUID jobId) {
        Job job = getJobById(phaseId, jobId);
        jobRepository.delete(job);
    }
    public Job assignUsers(UUID jobId, Set<UUID> userIds) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        Set<User> users = userService.findUsersByIds(userIds);
        job.setAssignedUsers(users);

        return jobRepository.save(job);
    }

}
