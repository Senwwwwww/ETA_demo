package com.example.EAT_demo.converter;

import com.example.EAT_demo.dto.DepartmentDTO;
import com.example.EAT_demo.pojo.Department;

@SuppressWarnings("all")
public class DepartmentConverter {

    public static DepartmentDTO convertDepartment(Department department) {
        DepartmentDTO departmentDTO = new DepartmentDTO();

        departmentDTO.setDepartmentId(department.getDepartmentId());
        departmentDTO.setDepartmentName(department.getDepartmentName());
        departmentDTO.setDepartmentIsDelete(department.getDepartmentIsDelete());
        departmentDTO.setDepartmentContent(department.getDepartmentContent());
        return departmentDTO;
    }

    public static Department convertDepartment(DepartmentDTO departmentDTO) {
        Department department = new Department();

        department.setDepartmentId(departmentDTO.getDepartmentId());
        department.setDepartmentName(departmentDTO.getDepartmentName());
        department.setDepartmentContent(departmentDTO.getDepartmentContent());
        return department;
    }
}
