package com.example.EAT_demo.dto;

import com.example.EAT_demo.pojo.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class FeedbackDTO {
    private long feedbackId;

    private UserDTO userDTO;

    @JsonProperty("content")
    private String feedbackContent;

    @JsonProperty("type")
    private String feedbackType;

    private Timestamp feedbackTime;

    private String feedbackStatus;

    private int feedbackIsDelete;
}