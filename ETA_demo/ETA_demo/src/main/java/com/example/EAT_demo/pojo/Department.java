package com.example.EAT_demo.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "department")
public class Department {

    @Id
    private long departmentId;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "department_content")
    private String departmentContent;

    @Column(name = "department_is_delete")
    private int departmentIsDelete = 0;

}
