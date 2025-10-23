package com.example.demo1.controller;

import com.example.demo1.entity.Projects;
import com.example.demo1.service.ProjectsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080"})
@RestController
@RequestMapping("/project-details")
public class ProjectsController {

    private final ProjectsService projectsService;

    public ProjectsController(ProjectsService projectsService) {
        this.projectsService = projectsService;
    }

    @GetMapping("/get-all-projects")
    public List<Projects> getAllProjects() {
        return projectsService.getAllProjects();
    }

    @PostMapping("/add-project")
    public Projects createProject(@RequestBody Projects project) {
        return projectsService.createProject(project);
    }

    // ✅ POST multiple projects
    @PostMapping("/add-multiple")
    public List<Projects> createMultipleProjects(@RequestBody List<Projects> projects) {
        return projectsService.createMultipleProjects(projects);
    }



    @GetMapping("/get-project/{id}")
    public Projects getProject(@PathVariable UUID id) {
        return projectsService.getProjectById(id);
    }

    @DeleteMapping("/delete-project/{id}")
    public void deleteProject(@PathVariable UUID id) {
        projectsService.deleteProject(id);
    }


    // ✅ PUT - Full update (replace entire project)
    @PutMapping("/update-project/{id}")
    public Projects updateProject(@PathVariable UUID id, @RequestBody Projects updatedProject) {
        return projectsService.updateProject(id, updatedProject);
    }

    // ✅ PATCH - Partial update (only update specific fields)
    @PatchMapping("/update-project-partial/{id}")
    public Projects partiallyUpdateProject(@PathVariable UUID id, @RequestBody Projects partialProject) {
        return projectsService.partiallyUpdateProject(id, partialProject);
    }
}
