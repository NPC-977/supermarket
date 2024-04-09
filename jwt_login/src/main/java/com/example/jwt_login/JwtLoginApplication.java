package com.example.jwt_login;

import com.example.jwt_login.jwt.JwTUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({JwTUtils.class})
public class JwtLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtLoginApplication.class, args);
    }

}
