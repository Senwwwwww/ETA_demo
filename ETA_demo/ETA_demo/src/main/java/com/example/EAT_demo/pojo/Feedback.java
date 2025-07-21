package com.example.EAT_demo.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@SuppressWarnings("all")
@Data
@AllArgsConstructor
@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long feedbackId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "feedback_content")
    private String feedbackContent;

    @Column(name = "feedback_type")
    private String feedbackType;

    @Column(name = "feedback_status")
    private String feedbackStatus = "未处理";

    @Column(nullable = false, updatable = false,name = "feedback_time") // 不可为空且不可更新
    private Timestamp feedbackTime;

    @Column(name = "feedback_is_delete")
    private int feedbackIsDelete = 0;

    public Feedback() {
        this.feedbackTime = new Timestamp(System.currentTimeMillis()); // 设置当前时间
    }

}
