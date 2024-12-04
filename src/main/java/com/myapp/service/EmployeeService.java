package com.myapp.service;

import com.myapp.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllemployee();
    void deleteEmployeeById(Integer employeeId);
    boolean deleteEmployeeByIdFull(Integer employeeId);
}
