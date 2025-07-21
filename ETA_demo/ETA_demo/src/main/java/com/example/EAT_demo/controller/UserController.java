package com.example.EAT_demo.controller;

import com.example.EAT_demo.Response;
import com.example.EAT_demo.aop.RecordBehavior;
import com.example.EAT_demo.dto.PersonalDTO;
import com.example.EAT_demo.dto.PersonalInformationDTO;
import com.example.EAT_demo.pojo.Role;
import com.example.EAT_demo.pojo.User;
import com.example.EAT_demo.dto.UserDTO;
import com.example.EAT_demo.service.PersonalInfoService;
import com.example.EAT_demo.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("all")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PersonalInfoService personalInfoService;

    // 获取所有某角色的用户
    @GetMapping("/user/role/{role}")
    @CrossOrigin
    public Response<List<User>> getUsersByRole(@PathVariable Role role) {
        return Response.newSuccess(userService.getUsersByRole(role));
    }

    // 根据用户名获取用户信息
    @GetMapping("/user/name/{username}")
    @CrossOrigin
    public Response<UserDTO> getUserByUsername(@PathVariable String username) {
        return Response.newSuccess(userService.getUserByUsername(username));
    }

    // 根据用户ID获取用户信息
    @GetMapping("/user/id/{id}")
    @CrossOrigin
    public Response<UserDTO> getUserById(@PathVariable Long id) {
        return Response.newSuccess(userService.getUserById(id));
    }

    //根据用户ID获取用户信息和个人信息
    @GetMapping("/user/personalInfo/id/{id}")
    @CrossOrigin
    public Response<PersonalDTO> getUserAndPersonalInformationById(@PathVariable Long id) {
        return Response.newSuccess(userService.getUserAllInfoById(id));
    }

    @GetMapping("/user/admin/getAllUserInfo")
    @CrossOrigin
    public Response<List<UserDTO>> getAllUserInfo() {
        return Response.newSuccess(userService.getAllUserInfo());
    }

    // 添加新用户
    @PostMapping("/user/add")
    @CrossOrigin
    public Response<Long> addNewUser(@RequestBody UserDTO userDTO) {
        return Response.newSuccess(userService.addNewUser(userDTO));
    }


    // 根据用户ID删除用户（逻辑删除）
    @DeleteMapping("/user/admin/deleteUserInfo")
    @CrossOrigin
    public Response<Long> deleteUserById(@RequestParam Long userId) {
        return Response.newSuccess(userService.deleteUserById(userId));
    }

    // 根据用户ID更新用户信息
    @PutMapping("/user/update/{id}")
    @CrossOrigin
    public Response<UserDTO> updateUserById(@PathVariable long id,
                                            @RequestParam(required = false) String username,
                                            @RequestParam(required = false) String password,
                                            @RequestParam(required = false) String email,
                                            @RequestParam(required = false) String name) {
        return Response.newSuccess(userService.updateUserById(id, username, password, email, name));
    }

    // 根据用户ID更新或添加用户信息
    @PutMapping("/user/addAndUpdatePersonalInfo/{id}")
    @CrossOrigin
    public Response<PersonalInformationDTO> updateUserById(@PathVariable long id,@RequestBody PersonalInformationDTO personalInformationDTO) {
        return Response.newSuccess(personalInfoService.addAndUpdatePersonalInfo(personalInformationDTO, id));
    }

    //修改密码updateUserPasswordById
    @RecordBehavior(value = "修改密码")
    @PutMapping("/user/update/password/{id}")
    @CrossOrigin
    public Response<UserDTO> updateUserPasswordById(@PathVariable long id,
                                                    @RequestParam() String oldPassword,
                                                    @RequestParam() String password) {
        return Response.newSuccess(userService.updateUserPasswordById(id, oldPassword, password));
    }

    //登陆检测
    @RecordBehavior(value = "登陆系统")
    @PostMapping("/user/login")
    @CrossOrigin
    public Response<String> login(@RequestBody UserDTO userDTO) {

        String token = userService.loginUser(userDTO);
        return Response.newSuccessToken(token);
    }

    //注册
    @PostMapping("/user/login/register")
    @CrossOrigin
    public Response<Long> register(@RequestBody UserDTO userDTO) {
        return Response.newSuccess(userService.registerUser(userDTO));
    }

    //忘记密码
    @PostMapping("/user/login/RecoverPassword")
    @CrossOrigin
    public Response<Long> RecoverPassword(@RequestBody UserDTO userDTO) {
        return Response.newSuccess(userService.RecoverPassword(userDTO));
    }

    @PutMapping("/user/login/update/{id}")
    @CrossOrigin
    public Response<UserDTO> changePassword(@PathVariable long id,
                                            @RequestParam(required = false) String username,
                                            @RequestParam(required = false) String password,
                                            @RequestParam(required = false) String email,
                                            @RequestParam(required = false) String name) {
        return Response.newSuccess(userService.updateUserById(id, username, password, email, name));
    }

    //设置用户名
    @PutMapping("/user/login/setName/{id}")
    @CrossOrigin
    public Response<UserDTO> setName(@PathVariable long id,
                                     @RequestParam(required = false) String username,
                                     @RequestParam(required = false) String password,
                                     @RequestParam(required = false) String email,
                                     @RequestParam(required = false) String name) {
        return Response.newSuccess(userService.updateUserById(id, username, password, email, name));
    }

    //验证邮箱
    @PostMapping("/user/bindEmail/{id}")
    @CrossOrigin
    public Response<String> EmailVerification(@PathVariable long id, @RequestParam() String email) {
        return Response.newSuccess(userService.EmailVerification(id, email));
    }

    //用户绑定
    @RecordBehavior("绑定员工id")
    @PutMapping("/user/bindUser/{id}")
    @CrossOrigin
    public Response<UserDTO> BindUser(@PathVariable long id, @RequestParam() long employeeId, @RequestParam() long departmentId, @RequestParam String realName) {
        return Response.newSuccess(userService.UserBind(id,employeeId,departmentId,realName));
    }

    @PostMapping("/user/logout")
    @CrossOrigin
    public Response<String> logout() {
       return Response.newSuccess("注销成功");
    }


}

