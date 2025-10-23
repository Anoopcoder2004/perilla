
package com.example.demo1.controller;

import com.example.demo1.entity.Job;
import com.example.demo1.service.JobService;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080"})
@RestController
@RequestMapping("/projects/{projectId}/phases/{phaseId}/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public List<Job> getAllJobs(@PathVariable UUID phaseId) {
        return jobService.getJobsByPhase(phaseId);
    }

    @PostMapping
    public Job addJob(@PathVariable UUID id, @RequestBody Job job) {
        return  jobService.createJob(id, job, new HashSet<>());
    }

    @GetMapping("/{jobId}")
    public Job getJob(@PathVariable UUID phaseId, @PathVariable UUID jobId) {
        return jobService.getJobById(phaseId, jobId);
    }

    @PutMapping("/{jobId}")
    public Job updateJob(@PathVariable UUID phaseId, @PathVariable UUID jobId, @RequestBody Job updatedJob) {
        return jobService.updateJob(phaseId, jobId, updatedJob);
    }

    @DeleteMapping("/{jobId}")
    public void deleteJob(@PathVariable UUID phaseId, @PathVariable UUID jobId) {
        jobService.deleteJob(phaseId, jobId);
    }
    // New endpoint for assigning users
    @PostMapping("/{jobId}/assign-users")
    public Job assignUsers(
            @PathVariable UUID jobId,
            @RequestBody Set<UUID> userIds) {
        return jobService.assignUsers(jobId, userIds);
    }
}
