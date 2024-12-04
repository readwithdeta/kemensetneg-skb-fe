package com.myapp.service;

import com.myapp.entity.Employee;
import com.myapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllemployee(){
        return employeeRepository.findAll();
    }

    @Override
    public void deleteEmployeeById(Integer employeeId){
        jdbcTemplate.update("update employee set is_deleted = true where employee_id = ? ",employeeId);
    }

    @Override
    public boolean deleteEmployeeByIdFull(Integer employeeId) {
        if (employeeRepository.existsById(employeeId)) {
            employeeRepository.deleteById(employeeId);
            return true;
        } else {
            return false;  // Employee tidak ditemukan
        }
    }
}
