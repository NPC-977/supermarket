package com.example.jwt_login.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证入口点，当用户未认证或认证不通过时，需要重定向到AuthenticationEntryPoint进行认证
 */
@Component
public class JwTAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    //启动身份验证方案，参数request是遇到了认证异常authException用户请求，response是将要返回给客户的响应，相应的认证方案逻辑会修改response并返回给用户引导用户进入认证流程。
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json");
        PrintWriter writer =response.getWriter();
        Map result = new HashMap<String,String>();
        result.put("msg","No Authentication!");
        writer.write(new ObjectMapper().writeValueAsString(result));
    }
}
