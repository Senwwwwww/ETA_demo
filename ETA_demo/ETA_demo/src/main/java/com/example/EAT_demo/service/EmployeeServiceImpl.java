package com.example.EAT_demo.service;

import com.example.EAT_demo.converter.EmployeeConverter;
import com.example.EAT_demo.dao.DepartmentRepository;
import com.example.EAT_demo.dao.EmployeeRepository;
import com.example.EAT_demo.dao.UserRepository;
import com.example.EAT_demo.dto.EmployeeDTO;
import com.example.EAT_demo.pojo.Department;
import com.example.EAT_demo.pojo.Employee;
import com.example.EAT_demo.pojo.User;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("all")
@Service
public class EmployeeServiceImpl implements EmployeeService {


    @Resource
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Override
    public long addAndUpdateNewEmployeeInfo(EmployeeDTO employeeDTO) {
        Employee employeeINDB = employeeRepository.findByEmployeeId(employeeDTO.getEmployeeId());


        //如果已存在则更新，不存在添加
        if (employeeINDB != null) {
            employeeINDB.setRealName(employeeDTO.getRealName());
            employeeINDB.setDepartment(departmentRepository.findByDepartmentName(employeeDTO.getTask()));
            employeeRepository.save(employeeINDB);
            return employeeINDB.getEmployeeId();
        } else {
            Department department = departmentRepository.findByDepartmentName(employeeDTO.getTask());
            Employee employee = EmployeeConverter.convertEmployee(employeeDTO, department);
            employeeRepository.save(employee);
            return employee.getEmployeeId();
        }
    }

    @Override
    public List<EmployeeDTO> getAllEmployeeInfo() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(employee -> {
            EmployeeDTO dto = new EmployeeDTO();
            dto.setEmployeeId(employee.getEmployeeId());
            dto.setRealName(employee.getRealName());
            dto.setEmployeeIsDelete(employee.getEmployeeIsDelete()) ;
            dto.setUserId(employee.getUserId());
            if (employee.getDepartment() != null)
            {
                dto.setTask(employee.getDepartment().getDepartmentName());
            }else {
                dto.setTask("其他");
            }
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeInfo(Long employeeId) {
        Employee employees = employeeRepository.findByEmployeeId(employeeId);
        return EmployeeConverter.convertEmployee(employees);
    }


    @Override
    public long deleteEmployeeInfo(Long employeeId) {
        Employee employeeINDB = employeeRepository.findByEmployeeId(employeeId);
        //如果已存在则更新，不存在添加
        if (employeeINDB != null) {
            employeeRepository.delete(employeeINDB);
        }
        return employeeINDB.getEmployeeId();
    }



}
