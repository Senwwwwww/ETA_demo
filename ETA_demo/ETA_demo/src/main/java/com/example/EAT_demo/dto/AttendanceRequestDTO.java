package com.example.EAT_demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceRequestDTO {

    @JsonProperty("EmployeeId")
    private Long employeeId;

    @JsonProperty("ClockInDate")
    private Date ClockInDate;

    @JsonProperty("ClockInTime")
    private Time ClockInTime;

    @JsonProperty("Method")
    private Long Method;

    @JsonProperty("FingerPath")
    private String FingerPath;

    @JsonProperty("FacePath")
    private String FacePath;

}
