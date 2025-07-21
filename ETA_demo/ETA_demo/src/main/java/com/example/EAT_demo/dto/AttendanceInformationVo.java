package com.example.EAT_demo.dto;

import com.example.EAT_demo.pojo.Employee;
import com.example.EAT_demo.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceInformationVo {
    private String title;
    private String data;
    private String task;
    private EmployeeDTO employeeDTO;
    private String priority;
}
