package com.example.demo1.controller;

import com.example.demo1.entity.Department;
import com.example.demo1.repository.DepartmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DepartmentController{

    private final DepartmentRepository departmentRepository;

    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/departments")
public List<Department> getDepartment() {
    return departmentRepository.findAll();
}

@PostMapping("/departments")
public Department addDepartment(@RequestBody Department department ){
    return departmentRepository.save(department);
}
}