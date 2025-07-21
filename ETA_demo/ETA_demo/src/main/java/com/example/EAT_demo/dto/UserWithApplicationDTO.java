package com.example.EAT_demo.dto;

import com.example.EAT_demo.pojo.Bioinformatics;
import com.example.EAT_demo.pojo.Department;
import com.example.EAT_demo.pojo.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("all")

public class UserWithApplicationDTO {
    private long userId;

    private Bioinformatics bioinformatics;

    @JsonProperty("Md5password")
    private String password;

    private String username;

    private String email;

    private String name;

    private int userIsDelete;
}
