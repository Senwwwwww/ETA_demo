package com.example.EAT_demo.converter;

import com.example.EAT_demo.dao.UserRepository;
import com.example.EAT_demo.dto.UserBehaviorDTO;
import com.example.EAT_demo.pojo.User;
import com.example.EAT_demo.pojo.UserBehavior;
import org.springframework.beans.factory.annotation.Autowired;

public class UserBahaviorConverter {
    public static UserBehaviorDTO convertUserBehavior(UserBehavior userBehavior)
    {
        return new UserBehaviorDTO(userBehavior.getId(),userBehavior.getUser().getName(),userBehavior.getBehaviorTime(),userBehavior.getBehaviorData());
    }
    public static UserBehavior convertUserBehavior(UserBehaviorDTO userBehaviorDTO, User user)
    {
        return new UserBehavior(userBehaviorDTO.getId(),user,userBehaviorDTO.getBehaviorData(),userBehaviorDTO.getBehaviorTime());
    }
}
