package com.example.EAT_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBehaviorDTO {
    private long id;
    private String name;
    private Date behaviorTime;
    private String behaviorData;
}
