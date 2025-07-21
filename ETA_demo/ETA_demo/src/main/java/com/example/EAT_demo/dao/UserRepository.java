package com.example.EAT_demo.dao;


import com.example.EAT_demo.pojo.Employee;
import com.example.EAT_demo.pojo.Role;
import com.example.EAT_demo.pojo.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //根据用户名查找用户信息
    User findByUsername(String username);

    // 根据用户角色查找所有用户信息
    @Query("SELECT u FROM User u WHERE u.role = :role AND u.userIsDelete = 0")
    List<User> findByRole(Role role);

    User findByUserId(Long userId);

    User findByEmployee(Employee employee);
}
