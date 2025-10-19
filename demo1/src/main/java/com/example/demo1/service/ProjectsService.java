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

}
