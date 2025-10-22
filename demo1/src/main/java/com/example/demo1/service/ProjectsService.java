package com.example.demo1.service;

import com.example.demo1.entity.Projects;
import com.example.demo1.repository.ProjectsRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ProjectsService {

    private final ProjectsRepository projectsRepository;


    public ProjectsService(ProjectsRepository projectsRepository) {
        this.projectsRepository = projectsRepository;
    }

    public List<Projects> getAllProjects() {
        return projectsRepository.findAll();
    }

    public Projects createProject(Projects project) {
        return projectsRepository.save(project);
    }

    public Projects getProjectById(UUID id) {
        return projectsRepository.findById(id).orElse(null);
    }

    public void deleteProject(UUID id) {
        projectsRepository.deleteById(id);
    }
    public List<Projects> createMultipleProjects(List<Projects> projects) {
        return projectsRepository.saveAll(projects);
    }
    public Projects updateProject(UUID id, Projects updatedProject) {
        Projects existing = projectsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        existing.setProjectCode(updatedProject.getProjectCode());
        existing.setProjectName(updatedProject.getProjectName());
        existing.setStartDate(updatedProject.getStartDate());
        existing.setEndDate(updatedProject.getEndDate());
        existing.setStatus(updatedProject.getStatus());
        existing.setPriority(updatedProject.getPriority());
        existing.setClientName(updatedProject.getClientName());
        existing.setBudget(updatedProject.getBudget());

        return projectsRepository.save(existing);
    }

    public Projects partiallyUpdateProject(UUID id, Projects partialProject) {
        Projects existing = projectsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        if (partialProject.getProjectCode() != null)
            existing.setProjectCode(partialProject.getProjectCode());
        if (partialProject.getProjectName() != null)
            existing.setProjectName(partialProject.getProjectName());

        if (partialProject.getStartDate() != null)
            existing.setStartDate(partialProject.getStartDate());
        if (partialProject.getEndDate() != null)
            existing.setEndDate(partialProject.getEndDate());
        if (partialProject.getStatus() != null)
            existing.setStatus(partialProject.getStatus());
        if (partialProject.getPriority() != null)
            existing.setPriority(partialProject.getPriority());
        if (partialProject.getClientName() != null)
            existing.setClientName(partialProject.getClientName());
        if (partialProject.getBudget() != 0)
            existing.setBudget(partialProject.getBudget());

        return projectsRepository.save(existing);
    }


}
