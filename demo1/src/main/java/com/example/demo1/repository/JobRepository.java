package com.example.demo1.repository;

import com.example.demo1.entity.Job;
import com.example.demo1.entity.Phase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JobRepository extends JpaRepository<Job, UUID> {
    List<Job> findByPhase(Phase phase);
}
