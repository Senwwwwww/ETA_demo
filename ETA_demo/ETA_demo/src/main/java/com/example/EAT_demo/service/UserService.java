package com.example.EAT_demo.service;

import com.example.EAT_demo.dto.PersonalDTO;
import com.example.EAT_demo.dto.UserDTO;
import com.example.EAT_demo.pojo.Role;
import com.example.EAT_demo.pojo.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("all")
@Service
public interface UserService {

    UserDTO getUserByUsername(String username);

    List<User> getUsersByRole(Role role);

    UserDTO getUserById(Long id);

    UserDTO getUserAndPersonalInformationById(Long user_id);

    PersonalDTO getUserAllInfoById(Long user_id);

    //返回用户所有信息
    List<UserDTO> getAllUserInfo();

    long addNewUser(UserDTO userDTO);

    Long deleteUserById(Long id);

    UserDTO deleteUserByUsername(String username);

    @Transactional
    UserDTO updateUserById(long id, String username, String password , String email,String name);

    //登录
    String loginUser(UserDTO userInfoDTO);

    //注册
    long registerUser(UserDTO userInfoDTO);

    //绑定邮箱
//    UserInfoDTO BindEmail(long id, String email);

    //找回密码
    long RecoverPassword(UserDTO userInfoDTO);

    @Transactional
    String EmailVerification(long id, String email);

    //更新密码
    @Transactional
    UserDTO updateUserPasswordById(long id, String oldPassword, String password);

    //用户绑定员工
    UserDTO UserBind(Long user_id, Long employee_id, Long department_id, String realName);
}
