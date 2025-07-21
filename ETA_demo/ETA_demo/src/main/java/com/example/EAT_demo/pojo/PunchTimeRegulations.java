package com.example.EAT_demo.pojo;

import com.example.EAT_demo.Util.IDutils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "punch_time_regulations")
public class PunchTimeRegulations {

    @Id
    @Column(name = "admin_id")
    private long adminId = 1;

    @Column(name = "clock_in_time")
    private Time clockInTime;

    @Column(name = "clock_out_time")
    private Time clockOutTime;

    @Column(name = "clock_be_late")
    private Time clockBeLate;

    @Column(name = "leave_process")
    private String leaveProcess;

    @Column(name = "absenteeism_policy")
    private String absenteeismPolicy;

    @Column(name = "overtime_rules")
    private String overtimeRules;
}
