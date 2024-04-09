package com.example.jwt_login.config;

import com.example.jwt_login.jwt.JwTAuthenticationEntryPoint;
import com.example.jwt_login.jwt.JwTFilter;
import com.example.jwt_login.jwt.JwTLoginFilter;
import com.example.jwt_login.jwt.JwTUtils;
import com.example.jwt_login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * WebSecurityConfigurerAdapter用来对安全策略进行配置
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwTUtils jwTUtils;

    @Autowired
    private UserService userService;
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    private JwTAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    /**
     *配置不需要登录（认证）就可以访问的资源，一般是静态资源，不走 Spring Security 过滤器链
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**","/images/**");
    }

    /**
     * 进行自定义安全访问策略
     * 应用程序的每个URL都要求用户通过认证，我们可以通过给http.authorizeRequests()方法添加子方法的方式为每个URL指定自定义要求。
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()  //允许基于使用HttpServletRequest限制访问
                .antMatchers("/employee/**")
                .hasRole("employee")
                .antMatchers("/admin/**")
                .hasRole("admin")
                .antMatchers(HttpMethod.POST,"/login")
                .permitAll()
                .antMatchers(HttpMethod.POST,"/public/**")
                .permitAll()
                .antMatchers(HttpMethod.GET,"/public/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .and()
                .addFilterBefore(new JwTLoginFilter("/login",authenticationManager(),jwTUtils), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwTFilter(jwTUtils.getSecret(),jwtAuthenticationEntryPoint),UsernamePasswordAuthenticationFilter.class)
                .csrf().disable();

    }
    /**
     * 角色继承，上级具备下级的所有权限
     */
    @Bean
    RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        hierarchy.setHierarchy("ROLE_admin > ROLE_employee");
        return hierarchy;
    }
}
