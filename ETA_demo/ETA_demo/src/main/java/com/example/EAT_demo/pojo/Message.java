package com.example.EAT_demo.pojo;

import com.example.EAT_demo.Util.IDutils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@Entity
@Table(name = "message")
public class Message {
    @Id
    private long messageId = IDutils.getID();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, updatable = false,name = "message_time")
    private Timestamp messageTime;

    @Column(name = "message_content")
    private String messageContent;

    @Column(name = "message_status")
    private String messageStatus = "未读";

    public Message() {
        this.messageTime = new Timestamp(System.currentTimeMillis()); // 设置当前时间
    }
}
