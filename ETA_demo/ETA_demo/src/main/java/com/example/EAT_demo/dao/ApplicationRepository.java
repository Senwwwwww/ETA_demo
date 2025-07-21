package com.example.EAT_demo.dao;

import com.example.EAT_demo.pojo.Application;
import com.example.EAT_demo.pojo.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @EntityGraph(
            attributePaths = {"user"}
    )


    List<Application> findByUser(User user);
}
