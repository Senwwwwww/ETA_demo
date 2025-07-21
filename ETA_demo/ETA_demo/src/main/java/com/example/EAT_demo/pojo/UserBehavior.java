package com.example.EAT_demo.pojo;

import com.example.EAT_demo.Util.IDutils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("all")
@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Table(name = "user_behavior")
public class UserBehavior {
    @Id
    @Column(name = "id")
    private long id= IDutils.getID();
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "behavior_data")
    private String behaviorData;
    @Column(name = "behavior_time")
    private Date behaviorTime;


}
