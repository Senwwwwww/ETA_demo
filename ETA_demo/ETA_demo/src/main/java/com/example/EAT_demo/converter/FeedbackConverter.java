package com.example.EAT_demo.converter;

import com.example.EAT_demo.dto.FeedbackDTO;
import com.example.EAT_demo.dto.UserDTO;
import com.example.EAT_demo.pojo.Feedback;


@SuppressWarnings("all")
public class FeedbackConverter {
    public static FeedbackDTO convertFeedback(Feedback feedback) {
        FeedbackDTO feedbackDTO = new FeedbackDTO();

        feedbackDTO.setFeedbackId(feedback.getFeedbackId());
        feedbackDTO.setFeedbackContent(feedback.getFeedbackContent());
        feedbackDTO.setFeedbackTime(feedback.getFeedbackTime());
        feedbackDTO.setFeedbackType(feedback.getFeedbackType());
        feedbackDTO.setFeedbackIsDelete(feedback.getFeedbackIsDelete());
        feedbackDTO.setFeedbackStatus(feedback.getFeedbackStatus());

        // 添加 User 信息到 FeedbackDTO
        if (feedback.getUser() != null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(feedback.getUser().getUserId());
            userDTO.setUsername(feedback.getUser().getUsername());
            userDTO.setEmail(feedback.getUser().getEmail());
            userDTO.setPassword(feedback.getUser().getPassword());
            userDTO.setRole(feedback.getUser().getRole());
            feedbackDTO.setUserDTO(userDTO);
        }

        return  feedbackDTO;
    }

    public static Feedback convertFeedback(FeedbackDTO feedbackDTO){
        Feedback feedback = new Feedback();

        feedback.setFeedbackType(feedbackDTO.getFeedbackType());
        feedback.setFeedbackContent(feedbackDTO.getFeedbackContent());
        feedback.setFeedbackIsDelete(feedbackDTO.getFeedbackIsDelete());

        return  feedback;
    }
}
