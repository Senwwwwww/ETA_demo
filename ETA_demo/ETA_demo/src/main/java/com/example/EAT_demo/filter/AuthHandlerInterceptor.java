package com.example.EAT_demo.filter;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.EAT_demo.Util.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Slf4j
@Component
public class AuthHandlerInterceptor implements HandlerInterceptor {
    @Autowired
    TokenUtil tokenUtil;
    @Value("${token.privateKey}")
    private String privateKey;
    @Value("${token.yangToken}")
    private Long yangToken;
    @Value("${token.oldToken}")
    private Long oldToken;

    // 定义不需要token验证的路径列表
    private final List<String> excludedPaths = Arrays.asList(
            "/items",           // 不检测token的路径
            "/auth/login",      // 登录路径
            "/auth/register",   // 注册路径
            "/public",          // 公共资源路径
            "/swagger-ui",      // Swagger文档路径
            "/v3/api-docs",     // API文档路径
            "/api/instrument",  // 修复：移除多余的配置，统一使用这个
            "/api/instruments"  // 添加：支持复数形式
    );

    /**
     * 权限认证的拦截操作.
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        log.info("=======进入拦截器========");

        // 获取请求路径
        String requestPath = httpServletRequest.getRequestURI();
        log.info("请求路径: " + requestPath);

        // 检查是否是排除的路径
        if (isExcludedPath(requestPath)) {
            log.info("路径 {} 无需token验证，直接放行", requestPath);
            return true;
        }

        // 如果不是映射到方法直接通过,可以访问资源.
        if (!(object instanceof HandlerMethod)) {
            return true;
        }

        //为空就返回错误
        String token = httpServletRequest.getHeader("token");
        if (null == token || "".equals(token.trim())) {
            log.info("token为空");
            throw new TokenExpiredException("token为空 请重新登录");
        }

        log.info("==============token:" + token);

        try {
            Map<String, String> map = tokenUtil.parseToken(token);
            String userId = map.get("userId");
            String userRole = map.get("userRole");
            long timeOfUse = System.currentTimeMillis() - Long.parseLong(map.get("timeStamp"));

            //1.判断 token 是否过期
            //年轻 token
            if (timeOfUse < yangToken) {
                log.info("年轻 token");
                log.info("Token 验证成功，用户ID: " + userId + ", 用户角色: " + userRole);
            }
            //老年 token 就刷新 token
            else if (timeOfUse >= yangToken && timeOfUse < oldToken) {
                log.info("老年 token，刷新token");
                httpServletResponse.setHeader("token", tokenUtil.getToken(userId, userRole));
            }
            //过期 token 就返回 token 无效.
            else {
                log.info("token已过期");
                throw new TokenExpiredException("token已过期");
            }

            //2.角色匹配.
            if ("user".equals(userRole)) {
                log.info("========user账户============");
                return true;
            }
            if ("employee".equals(userRole)) {
                log.info("========employee账户============");
                return true;
            }
            if ("admin".equals(userRole)) {
                log.info("========admin账户============");
                return true;
            }

            log.warn("未知的用户角色: " + userRole);
            return false;

        } catch (Exception e) {
            log.error("Token解析失败: " + e.getMessage());
            throw new TokenExpiredException("Token无效 请重新登录");
        }
    }

    /**
     * 检查路径是否在排除列表中
     * @param requestPath 请求路径
     * @return true如果路径被排除，false否则
     */
    private boolean isExcludedPath(String requestPath) {
        for (String excludedPath : excludedPaths) {
            // 精确匹配
            if (requestPath.equals(excludedPath)) {
                log.debug("精确匹配排除路径: " + excludedPath);
                return true;
            }
            // 前缀匹配 - 支持子路径
            if (requestPath.startsWith(excludedPath + "/")) {
                log.debug("前缀匹配排除路径: " + excludedPath);
                return true;
            }
        }
        return false;
    }
}