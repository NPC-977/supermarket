package com.example.jwt_login.jwt;


import com.example.jwt_login.pojo.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
/**
 * jwt登录（认证）处理过滤器
 */
public class JwTLoginFilter extends AbstractAuthenticationProcessingFilter {
    //封装jwt参数的对象
    private JwTUtils jwTUtils;

    public JwTLoginFilter(String defaultFilterProcessingUrl, AuthenticationManager authenticationManager,JwTUtils jwTUtils) {
        super(defaultFilterProcessingUrl);
        this.jwTUtils = jwTUtils;
        setAuthenticationManager(authenticationManager);

    }

    /**
     * 从request里获取账号和密码，开始认证
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, UsernameNotFoundException,ServletException {
        //获取json格式上传的数据，如果是key-value形式通过request.getParameter()
        User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
    }

    /**
     * 登录（认证）成功执行的方法
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        //获取登录用户的角色
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        StringBuffer sb = new StringBuffer();
        //获取用户权限
        for (GrantedAuthority authority : authorities) {
            sb.append(authority.getAuthority()).append(",");
        }
        //生成基于jwt的Token
        String jwtToken = Jwts.builder()
                .claim("authorities", sb)
                .setSubject(authResult.getName())
                .setExpiration(new Date(System.currentTimeMillis() + jwTUtils.getExpiredTime()))
                .signWith(SignatureAlgorithm.HS512, jwTUtils.getSecret())
                .compact();
        //生成返回客户端的结果
        Map result = new HashMap<String,String>();
        result.put("token",jwtToken);
        result.put("code",200);
        result.put("msg","登录成功!");
        //authorities为用户具有的权限
        result.put("authorities",sb);
        //写回客户端
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(result));
        out.flush();
        out.close();
    }
    /**
     * 登录（认证）失败执行的方法
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        Map result = new HashMap<String,String>();
        result.put("code",501);
        result.put("msg","登录失败!");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(result));
        out.flush();
        out.close();
    }
}
