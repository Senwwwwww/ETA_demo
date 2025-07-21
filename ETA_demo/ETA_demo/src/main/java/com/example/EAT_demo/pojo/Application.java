package com.example.EAT_demo.pojo;

import com.example.EAT_demo.Util.IDutils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Table(name = "application")
public class Application {

    @Id
    private long applicationId= IDutils.getID();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "application_type")
    private String applicationType;

    @Column(name = "application_status")
    private String applicationStatus = "待审批";

    @Column(name = "application_reason")
    private String applicationReason;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "end_time")
    private Timestamp endTime;

    @Column(name = "application_is_delete")
    private int applicationIsDelete = 0;
}
