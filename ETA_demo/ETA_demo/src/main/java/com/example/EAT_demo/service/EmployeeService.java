package com.example.EAT_demo.service;

import com.example.EAT_demo.dto.EmployeeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    long addAndUpdateNewEmployeeInfo(EmployeeDTO employeeDTO);

    List<EmployeeDTO> getAllEmployeeInfo();

    EmployeeDTO getEmployeeInfo(Long employeeId);

    long deleteEmployeeInfo(Long employeeId);
}
