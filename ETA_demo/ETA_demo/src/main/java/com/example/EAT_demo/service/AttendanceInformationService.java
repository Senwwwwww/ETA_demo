package com.example.EAT_demo.service;

import com.example.EAT_demo.dto.AttendanceInformationDTO;
import com.example.EAT_demo.dto.AttendanceInformationVo;
import com.example.EAT_demo.pojo.AttendanceInformation;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AttendanceInformationService {
    // 获取特定用户的考勤记录
    List<AttendanceInformationDTO> getUserAttendanceHistory(Long userId);
    // 获取所有用户的考勤记录
    List<AttendanceInformationDTO> getAllAttendanceHistory();

    void repairAttendance(AttendanceInformationVo attendanceInformationVo);
    // 筛选状态为待维修且任务为电力的考勤记录
    List<AttendanceInformation> findByClockInStatusAndTask( String ClockInStatus, String Task);
    List<AttendanceInformation> findByClockInStatus(String ClockInStatus);

    void updateTaskStatus(AttendanceInformationDTO attendanceInformationDTO);

    void publicAttendance(AttendanceInformationVo attendanceInformationVo);
}
