package com.example.EAT_demo.controller;

import com.example.EAT_demo.Response;
import com.example.EAT_demo.dto.EmployeeDTO;
import com.example.EAT_demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@SuppressWarnings("all")
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/user/addAndUpdateNewUserEmployeeInfo")
    @CrossOrigin
    public Response<Long> addAndUpdateNewUseEmployeeInfo(@RequestBody EmployeeDTO employeeDTO) {
        return Response.newSuccess(employeeService.addAndUpdateNewEmployeeInfo(employeeDTO));
    }

    @GetMapping("/user/admin/getAllEmployeeInfo")
    @CrossOrigin
    public Response<List<EmployeeDTO>> getAllEmployeeInfo() {
        return Response.newSuccess(employeeService.getAllEmployeeInfo());
    }

    @DeleteMapping("/user/admin/deleteEmployeeInfo")
    @CrossOrigin
    public Response<Long> deleteEmployeeInfo(@RequestParam Long employeeId) {
        return Response.newSuccess(employeeService.deleteEmployeeInfo(employeeId));
    }
    @GetMapping("/user/getEmployeeInfo/{employeeId}")
    @CrossOrigin
    public Response<EmployeeDTO> getEmployeeInfo(@PathVariable Long employeeId) {
        return Response.newSuccess(employeeService.getEmployeeInfo(employeeId));
    }

}
