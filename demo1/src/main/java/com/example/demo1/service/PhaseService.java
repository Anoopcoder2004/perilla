package com.example.demo1.service;

import com.example.demo1.entity.Phase;
import com.example.demo1.entity.Projects;
import com.example.demo1.repository.PhaseRepository;
import com.example.demo1.repository.ProjectsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
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
    public Phase updatePhase(UUID id, Phase updatedPhase) {
        Phase existingPhase = phaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Phase not found with id: " + id));

        existingPhase.setPhaseName(updatedPhase.getPhaseName());
        existingPhase.setPhaseCode(updatedPhase.getPhaseCode());
        existingPhase.setStartDate(updatedPhase.getStartDate());
        existingPhase.setEndDate(updatedPhase.getEndDate());
        existingPhase.setStatus(updatedPhase.getStatus());

        return phaseRepository.save(existingPhase);
    }
    // 🔹 NEW METHOD: Partial update (PATCH)
    public Phase patchPhase(UUID id, Map<String, Object> updates) {
        Phase existingPhase = phaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Phase not found with id: " + id));

        // Update only fields present in the request
        if (updates.containsKey("phaseName")) {
            existingPhase.setPhaseName((String) updates.get("phaseName"));
        }
        if (updates.containsKey("phaseCode")) {
            existingPhase.setPhaseCode((String) updates.get("phaseCode"));
        }
        if (updates.containsKey("startDate")) {
            existingPhase.setStartDate(LocalDate.parse((String) updates.get("startDate")));
        }
        if (updates.containsKey("endDate")) {
            existingPhase.setEndDate(LocalDate.parse((String) updates.get("endDate")));
        }
        if (updates.containsKey("status")) {
            existingPhase.setStatus((String) updates.get("status"));
        }

        // jobs are ignored unless explicitly included

        return phaseRepository.save(existingPhase);
    }
}
