package com.example.jwt_login.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
/**
 * 在用户权限不足时进行处理
 */
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        PrintWriter writer =response.getWriter();
        Map result = new HashMap<String,String>();
        result.put("code",200);
        result.put("msg","权限不足!");
        writer.write(new ObjectMapper().writeValueAsString(result));

    }
}
