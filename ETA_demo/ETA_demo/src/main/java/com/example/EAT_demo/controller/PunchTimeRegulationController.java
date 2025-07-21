package com.example.EAT_demo.controller;

import com.example.EAT_demo.Response;
import com.example.EAT_demo.aop.RecordBehavior;
import com.example.EAT_demo.pojo.PunchTimeRegulations;
import com.example.EAT_demo.service.PunchTimeRegulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("all")
@RestController
public class PunchTimeRegulationController {
    @Autowired
    private PunchTimeRegulationService punchTimeRegulationService;

    @RecordBehavior(value = "修改检修规则")
    @PostMapping("/user/admin/addAndUpdateAttendanceRules")
    @CrossOrigin
    public Response<PunchTimeRegulations> addAndUpdatePunchTimeRegulations(@RequestBody PunchTimeRegulations punchTimeRegulations) {
        return Response.newSuccess(punchTimeRegulationService.addAndUpdatePunchTimeRegulations(punchTimeRegulations));
    }

    @GetMapping("/user/getAttendanceRules")
    @CrossOrigin
    public Response<PunchTimeRegulations> getPunchTimeRegulations() {
        return Response.newSuccess(punchTimeRegulationService.getPunchTimeRegulations());
    }
}
