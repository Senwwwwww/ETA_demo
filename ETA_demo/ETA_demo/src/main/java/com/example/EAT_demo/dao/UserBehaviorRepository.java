package com.example.EAT_demo.dao;

import com.example.EAT_demo.pojo.Application;
import com.example.EAT_demo.pojo.UserBehavior;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserBehaviorRepository extends JpaRepository<UserBehavior, Long> {
    @Query("SELECT a FROM UserBehavior a WHERE a.user.userId = :userId order by a.behaviorTime DESC limit 10")
    List<UserBehavior> findByUserId(long userId);

}
