package com.example.demo1.controller;

import com.example.demo1.entity.Department;
import com.example.demo1.repository.DepartmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DepartmentController {

    private final DepartmentRepository departmentRepository;

    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/departments")
    public List<Department> getDepartment() {
        return departmentRepository.findAll();
    }

    @GetMapping("/departments/{id}")
    public Department getDepartmentById(@PathVariable Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found with id" + id);
        }
return department.get();
    }


@PostMapping("/departments")
public Department addDepartment(@RequestBody Department department ){
    return departmentRepository.save(department);
}
    // PUT — update a department completely
    @PutMapping("/departments/{id}")
    public Department updateDepartment(@PathVariable Long id, @RequestBody Department newDept) {
        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found with id " + id));
        dept.setName(newDept.getName());
        return departmentRepository.save(dept);
    }
    // PATCH — partial update
    @PatchMapping("/departments/{id}")
    public Department patchDepartment(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found with id " + id));
        if (updates.containsKey("name")) {
            dept.setName((String) updates.get("name"));
        }
        return departmentRepository.save(dept);
    }

    // DELETE a department
    @DeleteMapping("/departments/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found with id " + id));
        departmentRepository.delete(dept);
    }
}