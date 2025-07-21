package com.example.EAT_demo.dao;

import com.example.EAT_demo.pojo.AttendanceInformation;
import com.example.EAT_demo.pojo.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AttendanceInformationRepository extends JpaRepository<AttendanceInformation, Long> {
    @Query("SELECT a FROM AttendanceInformation a WHERE a.employee = :employee")
    List<AttendanceInformation> findByEmployee(@Param("employee") Employee employee);
//    筛选状态为待维修，类别为电力的申请
    @Query("SELECT a FROM AttendanceInformation a WHERE a.clockInStatus = :clockInStatus AND a.task = :task")
    List<AttendanceInformation> findByClockInStatusAndTask(@Param("clockInStatus") String clockInStatus, @Param("task") String Task);

    //筛选状态为待维修的申请
    @Query("SELECT a FROM AttendanceInformation a WHERE a.clockInStatus = :clockInStatus")
    List<AttendanceInformation> findByClockInStatus(@Param("clockInStatus") String clockInStatus);
}
