package com.example.demo1.repository;

import com.example.demo1.entity.Phase;
import com.example.demo1.entity.Projects;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PhaseRepository extends JpaRepository<Phase, UUID> {
    List<Phase> findByProject(Projects project);
}
