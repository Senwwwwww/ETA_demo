package com.example.EAT_demo.dto;

import com.example.EAT_demo.pojo.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("all")
public class EmployeeDTO {

    private long employeeId;

    private String realName;

    private int employeeIsDelete;

    private String task;

    private Long userId;

}
