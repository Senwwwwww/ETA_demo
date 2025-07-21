package com.example.EAT_demo.controller;

import com.example.EAT_demo.Response;
import com.example.EAT_demo.aop.RecordBehavior;
import com.example.EAT_demo.dto.ApplicationDTO;
import com.example.EAT_demo.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@SuppressWarnings("all")
@RestController
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/user/addNewApplicationInfo/{userId}")
    @CrossOrigin
    public Response<Long> addNewApplicationInfo(@PathVariable Long userId, @RequestBody ApplicationDTO applicationDTO) {
        return Response.newSuccess(applicationService.addNewApplicationInfo(applicationDTO,userId));
    }

    @GetMapping("/user/getAllUserApplicationInfo/{userId}")
    @CrossOrigin
    public Response<List<ApplicationDTO>> getAllUserApplicationInfo(@PathVariable Long userId) {
        return Response.newSuccess(applicationService.getUserApplicationHistory(userId));
    }

    @GetMapping("/user/admin/getAllApplicationInfo")
    @CrossOrigin
    public Response<List<ApplicationDTO>> getAllApplicationInfo() {
        return Response.newSuccess(applicationService.getAllApplicationHistory());
    }

    @RecordBehavior(value = "更新申请状态")
    @PutMapping("/user/admin/updateApplicationStatus")
    @CrossOrigin
    public Response<ApplicationDTO> updateApplicationStatus(@RequestBody ApplicationDTO applicationDTO) {
        return Response.newSuccess(applicationService.updateApplicationStatusById(applicationDTO));
    }

}
