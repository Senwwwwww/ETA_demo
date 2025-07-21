package com.example.EAT_demo.aop;

import com.example.EAT_demo.Util.TokenUtil;
import com.example.EAT_demo.dao.UserRepository;
import com.example.EAT_demo.pojo.User;
import com.example.EAT_demo.pojo.UserBehavior;
import com.example.EAT_demo.service.UserBehaviorService;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Aspect
@Component
public class UserBehaviorAspect {

    @Autowired
    private UserBehaviorService userBehaviorService;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private UserRepository userRepository;

    @After("@annotation(recordBehavior)")
    public void recordUserBehavior(JoinPoint joinPoint, RecordBehavior recordBehavior) {
        System.out.println("==================进入织入===================");

        // ✅ 1. 在主线程中安全获取 request & token
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes == null) return;

        HttpServletRequest request = attributes.getRequest();
        String authHeader = request.getHeader("Authorization");

        // 提前校验
        if (authHeader == null || !authHeader.startsWith("Bearer ")) return;

        String token = authHeader.substring(7);

        // 记录行为描述
        String behaviorDesc = recordBehavior.value();
        if (behaviorDesc.isEmpty()) {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            behaviorDesc = signature.getMethod().getName();
        }

        // ✅ 2. 异步记录日志（此处只传已提取的参数）

            try {
                // 解析 token 拿 user
                Map<String, String> tokenInfo = tokenUtil.parseToken(token);
                String userId = tokenInfo.get("userId");
                User currentUser = userRepository.findByUsername(userId);

                // 创建行为记录
                UserBehavior behavior = new UserBehavior();
                behavior.setBehaviorData(behaviorDesc);
                behavior.setUser(currentUser);
                behavior.setBehaviorTime(new java.util.Date());

                // 保存记录
                userBehaviorService.saveBehavior(behavior);
            } catch (Exception e) {
                System.err.println("用户行为记录失败: " + e.getMessage());
                e.printStackTrace();
            }

    }
}
