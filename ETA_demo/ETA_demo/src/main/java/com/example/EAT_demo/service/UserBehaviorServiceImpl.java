package com.example.EAT_demo.service;

import com.example.EAT_demo.converter.UserBahaviorConverter;
import com.example.EAT_demo.dao.UserBehaviorRepository;
import com.example.EAT_demo.dto.UserBehaviorDTO;
import com.example.EAT_demo.pojo.UserBehavior;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserBehaviorServiceImpl implements UserBehaviorService{
    @Autowired
    private UserBehaviorRepository userBehaviorRepository;

    @Override
    public List<UserBehavior> findByUserId(long userId) {
        return userBehaviorRepository.findByUserId(userId);
    }
    @Override
    @Transactional
    public void saveBehavior(UserBehavior behavior) {
        userBehaviorRepository.save( behavior);
    }

    @Override
    public List<UserBehaviorDTO> findTop10ByOrderByBehaviorTimeDesc(long userId) {
       List <UserBehavior> userBehaviors =userBehaviorRepository.findByUserId(userId);
       if(userBehaviors != null)
       {
           Collections.reverse(userBehaviors);
           return userBehaviors.stream()
                   .map(UserBahaviorConverter::convertUserBehavior)
                   .collect(Collectors.toList());
       }
        return null;
    }
}
