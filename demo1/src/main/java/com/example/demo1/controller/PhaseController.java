package com.example.demo1.controller;

import com.example.demo1.entity.Phase;
import com.example.demo1.service.PhaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080"})
@RestController
@RequestMapping("/projects/{projectId}/phases")
public class PhaseController {

    private final PhaseService phaseService;

    public PhaseController(PhaseService phaseService) {
        this.phaseService = phaseService;
    }

    @GetMapping
    public List<Phase> getAllPhases(@PathVariable UUID projectId) {
        return phaseService.getPhasesByProject(projectId);
    }

    @PostMapping
    public Phase addPhase(@PathVariable UUID projectId, @RequestBody Phase phase) {
        return phaseService.createPhase(projectId, phase);
    }

    @GetMapping("/{phaseId}")
    public Phase getPhase(@PathVariable UUID projectId, @PathVariable UUID phaseId) {
        return phaseService.getPhaseById(projectId, phaseId);
    }

    @PutMapping("/{phaseId}")
    public Phase updatePhase(@PathVariable UUID projectId, @PathVariable UUID phaseId, @RequestBody Phase updatedPhase) {
        return phaseService.updatePhase(projectId, phaseId, updatedPhase);
    }

    @DeleteMapping("/{phaseId}")
    public void deletePhase(@PathVariable UUID projectId, @PathVariable UUID phaseId) {
        phaseService.deletePhase(projectId, phaseId);
    }
}
