package com.example.EAT_demo.dto;

import com.example.EAT_demo.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class MessageDTO {

    private long messageId;

    private User user;

    private Timestamp messageTime;

    private String messageContent;

    private String messageStatus;
}

