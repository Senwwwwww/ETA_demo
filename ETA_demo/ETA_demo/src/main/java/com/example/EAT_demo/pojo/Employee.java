package com.example.EAT_demo.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    private long employeeId;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "employee_is_delete")
    private int employeeIsDelete = 0;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

}
