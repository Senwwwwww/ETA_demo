package com.example.EAT_demo.dto;

import com.example.EAT_demo.pojo.Employee;
import com.example.EAT_demo.pojo.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("all")
public class ApplicationDTO {
    private long applicationId;

    private UserDTO userDTO;

    @JsonProperty("type")
    private String applicationType;

    private String applicationStatus;

    @JsonProperty("reason")
    private String applicationReason;

    private Timestamp startTime;

    private Timestamp endTime;

    private int applicationIsDelete;
}

