package com.example.EAT_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceInformationDTO {

    private long AttendanceInfoId;

    private EmployeeDTO employeeDTO;

    private Date ClockInStartTime;

    private Date ClockInEndTime;

    private String ClockInStatus;

    private String Data;

    private String Task;

    private String Priority;
}
