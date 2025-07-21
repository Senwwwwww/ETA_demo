package com.example.EAT_demo.pojo;

import com.example.EAT_demo.Util.IDutils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("all")
@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Table(name = "user")
public class User {

    @Id
    @Column(name = "user_id")
    private long userId = IDutils.getID();

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="bioinfo_id")
    @JsonIgnore
    private Bioinformatics bioinformatics;


    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="employee_id")
    @JsonIgnore
    private Employee employee;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="Personal_info_id")
    @JsonIgnore
    private PersonalInformation personalInformation;

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "user_is_delete")
    private int userIsDelete = 0;

}
