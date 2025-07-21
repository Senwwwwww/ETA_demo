package com.example.EAT_demo.pojo;

import com.example.EAT_demo.Util.IDutils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@Table(name = "Personal_information")
public class PersonalInformation {

    @Id
    private long personalInfoId= IDutils.getID();

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "sex")
    private String sex;

    @Column(name = "birth_day")
    private Date birthDay;

    @Column(name = "person_is_delete")
    private int personIsDelete;

    @Column(name = "head_picture")
    private String headPicture;

}
