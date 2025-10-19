package com.example.demo1.controller;

import com.example.demo1.entity.Projects;
import com.example.demo1.service.ProjectsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/projects")
public class ProjectsController {

    private final ProjectsService projectsService;

    public ProjectsController(ProjectsService projectsService) {
        this.projectsService = projectsService;
    }

    @GetMapping
    public List<Projects> getAllProjects() {
        return projectsService.getAllProjects();
    }

    @PostMapping
    public Projects createProject(@RequestBody Projects project) {
        return projectsService.createProject(project);
    }

    // ✅ POST multiple projects
    @PostMapping("/multiple")
    public List<Projects> createMultipleProjects(@RequestBody List<Projects> projects) {
        return projectsService.createMultipleProjects(projects);
    }



    @GetMapping("/{id}")
    public Projects getProject(@PathVariable UUID id) {
        return projectsService.getProjectById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable UUID id) {
        projectsService.deleteProject(id);
    }
}
