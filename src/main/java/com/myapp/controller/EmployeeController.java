package com.myapp.controller;

import com.myapp.entity.Department;
import com.myapp.entity.Employee;
import com.myapp.repository.DepartmentRepository;
import com.myapp.repository.EmployeeRepository;
import com.myapp.service.EmployeeService;
import com.myapp.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/company")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeController(EmployeeRepository employeeRepository,
                              DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/getAllEmployee")
    public List<Employee> getAllEmployee() {
        return employeeService.getAllemployee();
    }

    @GetMapping("/getEmployeeById/{employeeId}")
    public Employee getEmployeeById(@PathVariable String employeeId) {
        return employeeRepository.findById(Integer.valueOf(employeeId)).get();
    }

    @PostMapping("/addNewEmployee")
    public Employee addNewEmployee(@RequestBody Employee employee) {
        Employee employee1 = new Employee();
        employee1 = employee;
        return employeeRepository.save(employee1);
    }

    @PostMapping("/editEmployee")
    public Employee editEmployee(@RequestBody Employee employee) {
        //Employee employee1 = employeeRepository.findById(Integer.valueOf(employee.getEmployeeId())).get();
        return employeeRepository.save(employee);
    }

    @PostMapping("/deleteEmployeeById/{employeeId}")
    public void deleteEmployeeById(@PathVariable String employeeId) {
        Employee employee = employeeRepository.findById(Integer.valueOf(employeeId)).get();
        employeeService.deleteEmployeeById(employee.getEmployeeId());
    }

    @DeleteMapping("/deleteEmployeeByIdFull/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer employeeId) {
        try {
            boolean isDeleted = employeeService.deleteEmployeeByIdFull(employeeId);
            if (isDeleted) {
                return ResponseEntity.ok("Employee deleted successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

    @PutMapping("/editEmployeeFull/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer employeeId, @RequestBody Employee updatedEmployee) {

        Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);
        if (!employeeOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }


        Employee existingEmployee = employeeOpt.get();

        if (updatedEmployee.getDepartment() != null) {
            Optional<Department> departmentOpt = departmentRepository.findById(updatedEmployee.getDepartment().getDepartmentId());
            if (!departmentOpt.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            existingEmployee.setDepartment(departmentOpt.get());
        }

        existingEmployee.setFirstName(updatedEmployee.getFirstName());
        existingEmployee.setLastName(updatedEmployee.getLastName());
        existingEmployee.setEmail(updatedEmployee.getEmail());
        existingEmployee.setPhoneNumber(updatedEmployee.getPhoneNumber());
        existingEmployee.setHireDate(updatedEmployee.getHireDate());

        Employee savedEmployee = employeeRepository.save(existingEmployee);

        return ResponseEntity.status(HttpStatus.OK).body(savedEmployee);
    }

}
