package com.example.EAT_demo.controller;

import com.example.EAT_demo.Response;
import com.example.EAT_demo.aop.RecordBehavior;
import com.example.EAT_demo.dto.AttendanceInformationDTO;
import com.example.EAT_demo.dto.AttendanceInformationVo;
import com.example.EAT_demo.pojo.AttendanceInformation;
import com.example.EAT_demo.service.AttendanceInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@SuppressWarnings("all")
@RestController
public class AttendanceInformationController {

    @Autowired
    private AttendanceInformationService attendanceInformationService;

    @GetMapping("/user/getAttendance/{userId}")
    @CrossOrigin
    public Response<List<AttendanceInformationDTO>> getUserAttendanceHistory(@PathVariable Long userId) {
        List<AttendanceInformationDTO> attendanceRecords = attendanceInformationService.getUserAttendanceHistory(userId);
        return Response.newSuccess(attendanceRecords);
    }


    @GetMapping("/user/getAllAttendanceInfo")
    @CrossOrigin
    public Response<List<AttendanceInformationDTO>> getAllAttendanceHistory() {
        List<AttendanceInformationDTO> allAttendanceRecords = attendanceInformationService.getAllAttendanceHistory();
        return Response.newSuccess(allAttendanceRecords);
    }

//    接受前端数据
    @PostMapping("api/repairTask")
    @CrossOrigin
    public Response<String> repairAttendance(@RequestBody AttendanceInformationVo attendanceInformationVo) {
        attendanceInformationService.repairAttendance(attendanceInformationVo);
        return Response.newSuccess("修复成功");
    }

    @PostMapping("/user/getAttendance/findByClockInStatusAndTask")
    @CrossOrigin
    public Response<List<AttendanceInformation>> findByClockInStatusAndTask(@RequestBody AttendanceInformation attendanceInformation) {
        List<AttendanceInformation> attendanceRecords = attendanceInformationService.findByClockInStatusAndTask(attendanceInformation.getClockInStatus(), attendanceInformation.getTask());
        return Response.newSuccess(attendanceRecords);
    }
    @PostMapping("/user/getAttendance/findByClockInStatus")
    @CrossOrigin
    public Response<List<AttendanceInformation>> findByClockInStatus(@RequestBody AttendanceInformation attendanceInformation) {
        List<AttendanceInformation> attendanceRecords = attendanceInformationService.findByClockInStatus(attendanceInformation.getClockInStatus());
        return Response.newSuccess(attendanceRecords);
    }

    @RecordBehavior(value = "更新检修信息")
    @PostMapping("/user/updateTaskStatus")
    @CrossOrigin
    public Response<String> updateTaskStatus(@RequestBody AttendanceInformationDTO attendanceInformationDTO) {
        attendanceInformationService.updateTaskStatus(attendanceInformationDTO);
        return Response.newSuccess("更新成功");
    }


    @PostMapping("api/publictask")
    @CrossOrigin
    public Response<String> publicAttendance(@RequestBody AttendanceInformationVo attendanceInformationVo) {
        attendanceInformationService.publicAttendance(attendanceInformationVo);
        return Response.newSuccess("发布成功");
    }

}
