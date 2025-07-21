package com.example.EAT_demo.pojo;

import com.example.EAT_demo.Util.IDutils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "attendance_information")
public class AttendanceInformation {

    @Id
    private long attendanceInfoId = IDutils.getID();

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "clock_in_start_time")
    private Date clockInStartTime;

    @Column(name = "clock_in_end_time")
    private Date clockInEndTime;

    @Column(name = "clock_in_status")
    private String clockInStatus = "待维修";

    @Column(name = "data")
    private String data;

    @Column(name = "task")
    private String task;

    @Column(name = "priority")
    private String priority;

}
