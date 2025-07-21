package com.example.EAT_demo.dao;

import com.example.EAT_demo.pojo.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByUserId(Long userId);
    Employee findByEmployeeId(Long employeeId);
}
