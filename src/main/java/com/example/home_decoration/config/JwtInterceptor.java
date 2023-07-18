package com.example.home_decoration.config;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.home_decoration.tools.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authToken = request.getHeader("Authorization");
        JSONObject json = new JSONObject();
        if (authToken != null) {
            String token = authToken.substring("Bearer".length() + 1).trim();
            try {
                JwtUtil.verify(token);
                return true;
            } catch (SignatureVerificationException e) {
                json.put("message", "无效签名");
            } catch (TokenExpiredException e) {
                json.put("message", "token过期");
            } catch (AlgorithmMismatchException e) {
                json.put("message", "token算法不一致");
            }
        }
        json.put("state", "1");
        json.put("message", "请先登录！");
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().append(json.toString());
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
