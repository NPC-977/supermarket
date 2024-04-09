package com.example.jwt_login.jwt;



import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * jwt Token认证处理过滤器
 */
public class JwTFilter extends GenericFilterBean {

    private String excludeUrls="/public/";

    private String secret="";

    public JwTFilter(String secret,JwTAuthenticationEntryPoint jwtAuthenticationEntryPoint){
        super();
        this.secret = secret;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }


    JwTAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    /**
    *  过滤器的过滤方法，这里从请求里提取token进行校验
    * */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            if (request.getMethod().equals("OPTIONS")) {
                System.out.println("浏览器的预请求的处理..");
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,OPTIONS,DELETE");
                response.setHeader("Access-Control-Max-Age", "3600");
                response.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers,Content-Type,Accept, Access-Control-Allow-Headers, Authorization, X-Requested-With, Access-Control-Allow-Methods, Access-Control-Allow-Origin");
               // response.setHeader("Access-Control-Allow-Headers", "*, *");
                return;
            }
            //获取请求uri
            String url = request.getRequestURI();
            // 判断请求uri是否需要过滤（方法在下面）
            if (url.indexOf(excludeUrls) != -1) {
                // 不需要，放行
                filterChain.doFilter(request, response);
                return;
            }
            //提取Header里的Authorization字段
            String jwtToken = request.getHeader("Authorization");
           if(jwtToken==null||jwtToken.equals("null")){
                //没有携带Token
               writeToClient(request,response,501,"您尚未认证，请重新登录!");
                return;
            }
            //设置jwt签名密钥，以及替换token里的Bearer字符为空串（生成方法里可能包含该字符串）
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken.replace("Bearer", ""));
            Claims claims = claimsJws.getBody();
            //得到用户名
            String username = claims.getSubject();
            //得到用户权限，这里权限是用逗号分隔
            List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
            //创建基于用户名和用户权限的认证信息，此处不需要密码
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null, authorities);
            //将当前认证信息存入SecurityContext
            SecurityContextHolder.getContext().setAuthentication(token);
            //过滤器继续执行
            filterChain.doFilter(servletRequest, servletResponse);
       }catch (AuthenticationException e){
            jwtAuthenticationEntryPoint.commence(request,response,e);
        }catch (ExpiredJwtException e2){
            System.out.println("---token过期----");
            writeToClient(request,response,501,"认证过期，请重新登录!");
        }catch (SignatureException e3){
            System.out.println("---token异常----");
            writeToClient(request,response,501,"认证无效，请重新登录!");
        }catch (MalformedJwtException e3){
            System.out.println("---token异常----");
            writeToClient(request,response,501,"认证无效，请重新登录!");
        }
    }

    /**
     * 自定义的往客户端写消息的方法
     */
    private void writeToClient(HttpServletRequest request, HttpServletResponse response,int code ,String msg) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer =response.getWriter();
        Map result = new HashMap<String,String>();
        result.put("code",code);
        result.put("msg",msg);
        writer.write(new ObjectMapper().writeValueAsString(result));
    }
}
