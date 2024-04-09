package com.example.jwt_login.config;

import org.springframework.beans.factory.annotation.Value;

//该组件类通过@Value("${xxx}")注解从配置文件读取值
public class Constant {
    public static String FILE_UPLOAD_DIR;
//    @Value("${file.upload.dir}")
    @Value("${file.upload.dir}")
    public void setFileUploadDir(String fileUploadDir){
        FILE_UPLOAD_DIR = fileUploadDir;
    }
}
