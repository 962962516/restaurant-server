package com.restaurant.project.utils;

import com.restaurant.project.utils.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从请求头中获取Token
        String token = request.getHeader("Authorization");

        // 如果不是映射到方法不拦截，直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        // 验证Token
        if (token == null) {
            // Token不存在或不合法
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // 解析Token
        Claims claims = JwtTokenUtil.getClaimsFromToken(token);

        // 检查令牌是否过期
        if (claims.containsKey("exp")) {
            int expiration = (Integer) claims.get("exp");
            long currentTimestamp = System.currentTimeMillis() / 1000; // 当前时间的时间戳
            if (currentTimestamp > expiration) {
                // 令牌已过期，返回401未经授权的响应
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
        }

        if (claims.containsKey("userId") && claims.containsKey("username")) {
            // 验证成功，将用户信息存储在ThreadLocal中
            request.setAttribute("userId", claims.get("userId"));
            request.setAttribute("username", claims.get("username"));
            return true;
        } else {
            // Token中缺少必要的信息
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
    }
}

