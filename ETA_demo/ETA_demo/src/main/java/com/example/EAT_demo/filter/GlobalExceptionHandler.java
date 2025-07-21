package com.example.EAT_demo.filter;

import com.example.EAT_demo.Response;
import com.example.EAT_demo.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 用户 token 过期
     */
    @ExceptionHandler(value = TokenAuthExpiredException.class)
    @ResponseBody
    public Response<String> tokenExpiredExceptionHandler(TokenAuthExpiredException e){
        log.warn("token验证失败: {}", e.getMessage());
        return Response.newFail(e.getMessage()); // 使用Response封装异常信息返回给客户端
    }

    /**
     * 用户身份验证异常
     */
    @ExceptionHandler(value = UserAuthenticationException.class)
    @ResponseBody
    public Response<String> userAuthenticationExceptionHandler(UserAuthenticationException e) {
        log.warn("身份验证失败: {}", e.getMessage());
        return Response.newFail(e.getMessage()); // 使用Response封装异常信息返回给客户端
    }
    /**
     * 用户注册验证异常
     */
    @ExceptionHandler(value = UserAlreadyExistsException.class)
    @ResponseBody
    public Response<String> userAlreadyExistsExceptionHandler(UserAlreadyExistsException e) {
        log.warn("注册失败: {}", e.getMessage());
        return Response.newFail(e.getMessage());
    }

    @ExceptionHandler(value = EmailAuthExpiredException.class)
    @ResponseBody
    public Response<String> NoSuchEmailExceptionHandler(EmailAuthExpiredException e) {
        log.warn("邮箱验证失败: {}", e.getMessage());
        return Response.newFail(e.getMessage());
    }

    @ExceptionHandler(value = PassowordAuthExpiredException.class)
    @ResponseBody
    public Response<String> PasswordAuthExpiredExceptionHandler(PassowordAuthExpiredException e) {
        log.warn("密码验证失败: {}", e.getMessage());
        return Response.newFail(e.getMessage());
    }

    @ExceptionHandler(value = ApplicationContextException.class)
    @ResponseBody
    public Response<String> ApplicationContextExceptionHandler(ApplicationContextException e) {
        log.warn("申请失败: {}", e.getMessage());
        return Response.newFail(e.getMessage());
    }


    @ExceptionHandler(value = AttendanceContextException.class)
    @ResponseBody
    public Response<String> AttendanceContextExceptionHandler(AttendanceContextException e) {
        log.warn("打卡失败: {}", e.getMessage());
        return Response.newFail(e.getMessage());
    }
}
