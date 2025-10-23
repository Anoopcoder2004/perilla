package com.example.demo1.repository;

import com.example.demo1.entity.Phase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PhaseRepository extends JpaRepository<Phase, UUID> {
    List<Phase> findByProject_ProjectId(UUID projectId);
    Optional<Phase> findByPhaseIdAndProject_ProjectId(UUID phaseId, UUID projectId);
}
