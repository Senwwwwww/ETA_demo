package com.example.EAT_demo.dao;

import com.example.EAT_demo.pojo.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByDepartmentId(Long id);
    Department findByDepartmentName(String name);
}
