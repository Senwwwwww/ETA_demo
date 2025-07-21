package com.example.EAT_demo.dao;

import com.example.EAT_demo.pojo.Feedback;
import com.example.EAT_demo.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findAllByUser(User user);
}
