package com.example.jwt_login.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

//该配置类实现了WebMvcConfigurer接口，
// 通过addResourceHandlers方法配置本地资源映射路径，
// 将url与物理路径进行关联
public class MyWebMvcConfigurer {
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        //配置本地资源映射路径，将url与物理路径进行关联
        registry.addResourceHandler("/image/**")
                .addResourceLocations("file"+Constant.FILE_UPLOAD_DIR+"image/");
    }
}
