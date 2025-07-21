package com.example.EAT_demo.service;

import com.example.EAT_demo.dto.UserBehaviorDTO;
import com.example.EAT_demo.pojo.UserBehavior;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserBehaviorService {
    List<UserBehavior> findByUserId(long userId);
    void saveBehavior(UserBehavior behavior); // 添加保存方法
//    获取前10个用户行为
    List<UserBehaviorDTO> findTop10ByOrderByBehaviorTimeDesc(long userId);
}
