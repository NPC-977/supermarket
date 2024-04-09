package com.example.jwt_login.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 封装jwt基本参数的组件
 */
@Component
@Data
@ConfigurationProperties(prefix = "jwt") //自动从配置文件匹配jwt开头的参数
public class JwTUtils {
    //jwt密钥
    private String secret;
    //过期时间
    private long expiredTime;


}
