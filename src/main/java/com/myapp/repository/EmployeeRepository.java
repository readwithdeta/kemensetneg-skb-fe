package com.myapp.repository;

import com.myapp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;


public interface EmployeeRepository extends JpaRepository<Employee,Integer>{

    @Query(
            value = "SELECT * FROM employee where employee_id = :id",
            nativeQuery = true)
    Employee findEmployeeById(@Param(value = "id") String id);
}
