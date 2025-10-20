package com.example.demo1.controller;

import com.example.demo1.dto.JobDTO;
import com.example.demo1.entity.Job;
import com.example.demo1.service.JobService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/phase/{phaseId}")
    public List<Job> getJobsByPhase(@PathVariable UUID phaseId) {
        return jobService.getJobsByPhase(phaseId);
    }
    @PostMapping("/phase/{phaseId}")
    public Job createJob(@PathVariable UUID phaseId, @RequestBody JobDTO jobDTO) {
        return jobService.createJobFromDTO(phaseId, jobDTO);
    }

    // 🆕 Update existing job
    @PutMapping("/{jobId}")
    public Job updateJob(@PathVariable UUID jobId, @RequestBody Job job) {
        return jobService.updateJob(jobId, job); // 🆕 Calls service updateJob
    }

    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable UUID id) {
        jobService.deleteJob(id);
    }
}
