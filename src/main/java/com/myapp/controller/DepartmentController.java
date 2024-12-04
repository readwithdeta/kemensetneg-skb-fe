package com.myapp.controller;

import com.myapp.entity.Department;
import com.myapp.entity.Employee;
import com.myapp.repository.DepartmentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/company/department")
public class DepartmentController {
    private final DepartmentRepository departmentRepository;

    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/getAllDepartment")
    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }
}
