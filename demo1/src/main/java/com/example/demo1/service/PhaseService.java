package com.example.demo1.service;

import com.example.demo1.entity.Phase;
import com.example.demo1.entity.Projects;
import com.example.demo1.repository.PhaseRepository;
import com.example.demo1.repository.ProjectsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PhaseService {

    private final PhaseRepository phaseRepository;
    private final ProjectsRepository projectsRepository;

    public PhaseService(PhaseRepository phaseRepository, ProjectsRepository projectsRepository) {
        this.phaseRepository = phaseRepository;
        this.projectsRepository = projectsRepository;
    }

    // Get all phases
    public List<Phase> getAllPhases() {
        return phaseRepository.findAll();
    }

    // Get phases by project
    public List<Phase> getPhasesByProject(UUID projectId) {
        Projects project = projectsRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        return phaseRepository.findByProject(project);
    }

    // Create a new phase
    public Phase createPhase(UUID projectId, Phase phase) {
        Projects project = projectsRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        phase.setProject(project);
        return phaseRepository.save(phase);
    }

    // Delete phase
    public void deletePhase(UUID phaseId) {
        phaseRepository.deleteById(phaseId);
    }
}
