package com.example.EAT_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("all")
public class DepartmentDTO {

    private long departmentId;

    private String departmentName;

    private String departmentContent;



    private int departmentIsDelete;
}
