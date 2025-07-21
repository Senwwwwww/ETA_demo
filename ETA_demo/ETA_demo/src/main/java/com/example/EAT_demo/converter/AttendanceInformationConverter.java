package com.example.EAT_demo.converter;

import com.example.EAT_demo.dto.AttendanceInformationDTO;
import com.example.EAT_demo.dto.EmployeeDTO;
import com.example.EAT_demo.pojo.AttendanceInformation;
import com.example.EAT_demo.pojo.Employee;

@SuppressWarnings("all")
public class AttendanceInformationConverter {
    
    public static AttendanceInformation convertAttendanceInformation(AttendanceInformationDTO attendanceInformationDTO, Employee employee) {
        AttendanceInformation attendanceInformation = new AttendanceInformation();
        attendanceInformation.setAttendanceInfoId(attendanceInformationDTO.getAttendanceInfoId());
        attendanceInformation.setClockInStartTime(attendanceInformationDTO.getClockInStartTime());
        attendanceInformation.setClockInEndTime(attendanceInformationDTO.getClockInEndTime());
        attendanceInformation.setClockInStatus(attendanceInformationDTO.getClockInStatus());
        attendanceInformation.setData(attendanceInformationDTO.getData());
        attendanceInformation.setTask(attendanceInformationDTO.getTask());
        attendanceInformation.setPriority(attendanceInformationDTO.getPriority());

        attendanceInformation.setEmployee(employee);
        return attendanceInformation;
    }

    public static AttendanceInformationDTO convertAttendanceInformation(AttendanceInformation attendanceInformation) {
        AttendanceInformationDTO attendanceInformationDTO = new AttendanceInformationDTO();

        attendanceInformationDTO.setAttendanceInfoId(attendanceInformation.getAttendanceInfoId());

        attendanceInformationDTO.setClockInStartTime(attendanceInformation.getClockInStartTime());
        attendanceInformationDTO.setClockInEndTime(attendanceInformation.getClockInEndTime());
        attendanceInformationDTO.setClockInStatus(attendanceInformation.getClockInStatus());
        attendanceInformationDTO.setData(attendanceInformation.getData());
        attendanceInformationDTO.setTask(attendanceInformation.getTask());
        attendanceInformationDTO.setPriority(attendanceInformation.getPriority());

        if (attendanceInformation.getEmployee() != null) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setEmployeeId(attendanceInformation.getEmployee().getEmployeeId());
            employeeDTO.setUserId(attendanceInformation.getEmployee().getUserId());
            employeeDTO.setRealName(attendanceInformation.getEmployee().getRealName());
            attendanceInformationDTO.setEmployeeDTO(employeeDTO);
        }

        return attendanceInformationDTO;
    }
}
