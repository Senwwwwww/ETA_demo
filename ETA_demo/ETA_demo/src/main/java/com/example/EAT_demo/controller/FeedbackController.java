package com.example.EAT_demo.controller;

import com.example.EAT_demo.Response;
import com.example.EAT_demo.aop.RecordBehavior;
import com.example.EAT_demo.dto.FeedbackDTO;
import com.example.EAT_demo.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("all")
@RestController
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;


    @PostMapping("/user/addNewFeedbackInfo/{userId}")
    @RecordBehavior(value = "提交反馈")
    @CrossOrigin
    public Response<Long> addNewFeedbackInfo(@PathVariable Long userId, @RequestBody FeedbackDTO feedbackDTO) {
        return Response.newSuccess(feedbackService.addNewFeedbackInfo(feedbackDTO,userId));
    }
    @PutMapping("/user/admin/updateFeedbackStatus/{id}")
    @CrossOrigin
    public Response<FeedbackDTO> updateFeedbackStatusById(@PathVariable long id, @RequestBody FeedbackDTO feedbackDTO) {
        return Response.newSuccess(feedbackService.updateFeedbackStatusById(id,feedbackDTO));
    }

    @GetMapping("/user/admin/getAllFeedbackInfo")
    @CrossOrigin
    public Response<List<FeedbackDTO>> getAllFeedbackInfo() {
        return Response.newSuccess(feedbackService.getAllFeedbackInfo());
    }

    @GetMapping("/user/getAllUserFeedbackInfo/{userId}")
    @CrossOrigin
    public Response<List<FeedbackDTO>> getAllUserFeedbackInfo(@PathVariable Long userId) {
        return Response.newSuccess(feedbackService.getAllUserFeedbackInfo(userId));
    }


    @DeleteMapping("/user/admin/deleteFeedbackInfo")
    @CrossOrigin
    public Response<Long> deleteFeedbackInfo(@RequestParam Long feedbackId) {
        return Response.newSuccess(feedbackService.deleteFeedbackInfo(feedbackId));
    }
}
