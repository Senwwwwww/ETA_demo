package com.example.EAT_demo.controller;

import com.example.EAT_demo.Response;
import com.example.EAT_demo.dto.DepartmentDTO;
import com.example.EAT_demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@SuppressWarnings("all")
@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/user/admin/addAndUpdateNewDepartmentInfo")
    @CrossOrigin
    public Response<Long> addAndUpdateNewUserDepartmentInfo(@RequestBody DepartmentDTO departmentDTO) {
        return Response.newSuccess(departmentService.addAndUpdateNewDepartmentInfo(departmentDTO));
    }

    @GetMapping("/user/admin/getAllDepartmentInfo")
    @CrossOrigin
    public Response<List<DepartmentDTO>> getAllDepartmentInfo() {
        return Response.newSuccess(departmentService.getAllDepartmentInfo());
    }

    @DeleteMapping("/user/admin/deleteDepartmentInfo")
    @CrossOrigin
    public Response<Long> deleteEmployeeInfo(@RequestParam Long departmentId) {
        return Response.newSuccess(departmentService.deleteDepartmentInfo(departmentId));
    }


}
