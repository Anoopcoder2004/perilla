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

    public List<Phase> getPhasesByProject(UUID projectId) {
        return phaseRepository.findByProject_ProjectId(projectId);
    }

    public Phase createPhase(UUID projectId, Phase phase) {
        Projects project = projectsRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        phase.setProject(project);
        return phaseRepository.save(phase);
    }

    public Phase getPhaseById(UUID projectId, UUID phaseId) {
        return phaseRepository.findByPhaseIdAndProject_ProjectId(phaseId, projectId)
                .orElseThrow(() -> new RuntimeException("Phase not found"));
    }

    public Phase updatePhase(UUID projectId, UUID phaseId, Phase updatedPhase) {
        Phase existing = getPhaseById(projectId, phaseId);
        existing.setPhaseName(updatedPhase.getPhaseName());
        existing.setPhaseCode(updatedPhase.getPhaseCode());
        existing.setStartDate(updatedPhase.getStartDate());
        existing.setEndDate(updatedPhase.getEndDate());
        existing.setStatus(updatedPhase.getStatus());
        return phaseRepository.save(existing);
    }

    public void deletePhase(UUID projectId, UUID phaseId) {
        Phase existing = getPhaseById(projectId, phaseId);
        phaseRepository.delete(existing);
    }
}
