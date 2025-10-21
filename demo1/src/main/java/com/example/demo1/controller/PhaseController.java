package com.example.demo1.controller;

import com.example.demo1.entity.Phase;
import com.example.demo1.service.PhaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/phases")
public class PhaseController {

    private final PhaseService phaseService;

    public PhaseController(PhaseService phaseService) {
        this.phaseService = phaseService;
    }

    @GetMapping
    public List<Phase> getAllPhases() {
        return phaseService.getAllPhases();
    }

    @GetMapping("/project/{projectId}")
    public List<Phase> getPhasesByProject(@PathVariable UUID projectId) {
        return phaseService.getPhasesByProject(projectId);
    }

    @PostMapping("/project/{projectId}")
    public Phase createPhase(@PathVariable UUID projectId, @RequestBody Phase phase) {
        return phaseService.createPhase(projectId, phase);
    }

    @DeleteMapping("/{id}")
    public void deletePhase(@PathVariable UUID id) {
        phaseService.deletePhase(id);
    }
    @PutMapping("/{id}")
    public Phase updatePhase(@PathVariable UUID id, @RequestBody Phase updatedPhase) {
        return phaseService.updatePhase(id, updatedPhase);
    }
    @PatchMapping("/{id}")
    public Phase patchPhase(@PathVariable UUID id, @RequestBody Map<String, Object> updates) {
        return phaseService.patchPhase(id, updates);
    }

}
