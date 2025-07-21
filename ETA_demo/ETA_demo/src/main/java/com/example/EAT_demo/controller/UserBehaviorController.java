package com.example.EAT_demo.controller;

import com.example.EAT_demo.Response;
import com.example.EAT_demo.dto.UserBehaviorDTO;
import com.example.EAT_demo.service.UserBehaviorService;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserBehaviorController {
    @Autowired
    private UserBehaviorService userBehaviorService;
    @GetMapping("/user/behavior/top10/{userId}")
    @CrossOrigin
    public Response<List<UserBehaviorDTO>> getTop10UserBehavior(@PathVariable long userId)
    {
        return Response.newSuccess(userBehaviorService.findTop10ByOrderByBehaviorTimeDesc(userId));
    }
}
