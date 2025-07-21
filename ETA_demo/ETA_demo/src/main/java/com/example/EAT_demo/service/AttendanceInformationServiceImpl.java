package com.example.EAT_demo.service;

import com.example.EAT_demo.converter.AttendanceInformationConverter;
import com.example.EAT_demo.converter.EmployeeConverter;
import com.example.EAT_demo.dao.AttendanceInformationRepository;
import com.example.EAT_demo.dao.DepartmentRepository;
import com.example.EAT_demo.dao.UserRepository;
import com.example.EAT_demo.dto.AttendanceInformationDTO;
import com.example.EAT_demo.dto.AttendanceInformationVo;
import com.example.EAT_demo.exception.AttendanceContextException;
import com.example.EAT_demo.pojo.AttendanceInformation;
import com.example.EAT_demo.pojo.Department;
import com.example.EAT_demo.pojo.Employee;
import com.example.EAT_demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("all")
@Service
public class AttendanceInformationServiceImpl implements AttendanceInformationService {
    @Autowired
    private AttendanceInformationRepository attendanceInformationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    // 获取特定用户在指定月份的考勤记录
    @Override
    public List<AttendanceInformationDTO> getUserAttendanceHistory(Long userId) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new AttendanceContextException("用户不存在");
        }
        Employee employee = user.getEmployee();
        if (employee == null) {
            throw new AttendanceContextException("用户未绑定员工");
        }

        List<AttendanceInformation> attendanceINDB = attendanceInformationRepository.findByEmployee(employee);

        if (attendanceINDB.isEmpty()) {
            throw new AttendanceContextException("没有考勤记录");
        }

        return attendanceINDB.stream()
                .map(AttendanceInformationConverter::convertAttendanceInformation)
                .collect(Collectors.toList());
    }


    // 获取所有用户的考勤记录
    @Override
    public List<AttendanceInformationDTO> getAllAttendanceHistory() {
        List<AttendanceInformation> attendanceINDB = attendanceInformationRepository.findAll();
        if (attendanceINDB.isEmpty()) {
            throw new AttendanceContextException("没有考勤记录");
        }

        return attendanceINDB.stream()
                .map(AttendanceInformationConverter::convertAttendanceInformation)
                .collect(Collectors.toList());
    }

    @Override
    public void repairAttendance(AttendanceInformationVo attendanceInformationVo) {
        Date clock_in_start_time=new Date();
        String data = attendanceInformationVo.getData();
        String task = attendanceInformationVo.getTask();
        if (data == null || task == null) {
            throw new AttendanceContextException("数据不完整");
        }
        Department department = departmentRepository.findByDepartmentName(attendanceInformationVo.getTask());
        Employee employee= EmployeeConverter.convertEmployee( attendanceInformationVo.getEmployeeDTO(),department);
        AttendanceInformation attendanceInformation = new AttendanceInformation();
        attendanceInformation.setEmployee(employee);
        attendanceInformation.setClockInStartTime(clock_in_start_time);
        attendanceInformation.setData(data);
        attendanceInformation.setTask(task);
        attendanceInformationRepository.save(attendanceInformation);
    }

    @Override
    public List<AttendanceInformation> findByClockInStatusAndTask(String ClockInStatus, String Task) {
        return attendanceInformationRepository.findByClockInStatusAndTask(ClockInStatus, Task);
    }

    @Override
    public List<AttendanceInformation> findByClockInStatus(String ClockInStatus) {
        return attendanceInformationRepository.findByClockInStatus(ClockInStatus);
    }

    @Override
    public void updateTaskStatus(AttendanceInformationDTO attendanceInformationDTO) {
        Department department;
        Employee employee;
        if(attendanceInformationDTO.getTask()!=null) {
            department = departmentRepository.findByDepartmentName(attendanceInformationDTO.getTask());
        }else {
            department = null;
        }
        if(attendanceInformationDTO.getEmployeeDTO()!=null) {
            employee = EmployeeConverter.convertEmployee(attendanceInformationDTO.getEmployeeDTO(), department);
        }else{
            employee = null;
        }
        AttendanceInformation attendanceInformation = AttendanceInformationConverter.convertAttendanceInformation(attendanceInformationDTO,employee);
        // 执行更新数据操作
        attendanceInformationRepository.save(attendanceInformation);
    }

    @Override
    public void publicAttendance(AttendanceInformationVo attendanceInformationVo) {
        Date clock_in_start_time=new Date();
        String data = attendanceInformationVo.getData();
        String task = attendanceInformationVo.getTask();
        if (data == null || task == null) {
            throw new AttendanceContextException("数据不完整");
        }
        Department department = departmentRepository.findByDepartmentName(attendanceInformationVo.getTask());
        AttendanceInformation attendanceInformation = new AttendanceInformation();
        attendanceInformation.setClockInStartTime(clock_in_start_time);
        attendanceInformation.setData(data);
        attendanceInformation.setTask(task);
        attendanceInformation.setPriority(attendanceInformationVo.getPriority());
        attendanceInformationRepository.save(attendanceInformation);
    }

}
