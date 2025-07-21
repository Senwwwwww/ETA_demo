package com.example.EAT_demo.converter;

import com.example.EAT_demo.dao.DepartmentRepository;
import com.example.EAT_demo.dto.EmployeeDTO;
import com.example.EAT_demo.pojo.Department;
import com.example.EAT_demo.pojo.Employee;
import com.example.EAT_demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverter {

    public static EmployeeDTO convertEmployee(Employee employee){

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setUserId(employee.getUserId());
        employeeDTO.setEmployeeId(employee.getEmployeeId());
        employeeDTO.setRealName(employee.getRealName());
        employeeDTO.setEmployeeIsDelete(employee.getEmployeeIsDelete());
        if (employee.getDepartment() != null){
            employeeDTO.setTask(employee.getDepartment().getDepartmentName());
        }
        employeeDTO.setTask("其他");
        return  employeeDTO;
    }



    public static Employee convertEmployee(EmployeeDTO employeeDTO,Department department){

        Employee employee = new Employee();
        employee.setEmployeeId(employeeDTO.getEmployeeId());
        employee.setUserId(employeeDTO.getUserId());
        employee.setDepartment(department);
        employee.setRealName(employeeDTO.getRealName());
        employee.setEmployeeIsDelete(employeeDTO.getEmployeeIsDelete());
        return  employee;
    }

}
