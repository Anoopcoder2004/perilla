package com.example.demo1.repository;

import com.example.demo1.entity.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ProjectsRepository extends JpaRepository<Projects, UUID> {
}
