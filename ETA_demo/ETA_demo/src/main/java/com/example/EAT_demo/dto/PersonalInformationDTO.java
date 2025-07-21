package com.example.EAT_demo.dto;

import com.example.EAT_demo.Util.IDutils;
import com.example.EAT_demo.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("all")

public class PersonalInformationDTO {

    private long personalInfoId;

    private Long userId;

    private String sex;

    private Date birthDay;

    private String headPicture;

    private int personIsDelete;
}
