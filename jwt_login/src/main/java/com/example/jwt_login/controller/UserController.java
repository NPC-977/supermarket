package com.example.jwt_login.controller;

import com.example.jwt_login.config.Constant;
import com.example.jwt_login.config.Result;
import com.example.jwt_login.pojo.Employee;
import com.example.jwt_login.pojo.JsonResult;
import com.example.jwt_login.pojo.User;
import com.example.jwt_login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin/test")
    public JsonResult<String> test1() {
        return new JsonResult<>(200, "success", "您可以访问admin相关内容");
    }

    @GetMapping("/teacher/test")
    public JsonResult<String> test2() {
        return new JsonResult<>(200, "success", "您可以访问teacher相关内容");
    }

    @GetMapping("/student/test")
    public JsonResult<String> test3() {
        return new JsonResult<>(200, "success", "您可以访问student相关内容");
    }

    @GetMapping("/public/test")
    public JsonResult<String> test4() {
        return new JsonResult<>(200, "success", "无需认证就可以访问相关内容");
    }

    @PostMapping("/admin/addemployee")
    public JsonResult<String> addUser(@RequestBody String jsonString) {
        try {
            JsonParser jsonParser = JsonParserFactory.getJsonParser();
            Map<String, Object> map = jsonParser.parseMap(jsonString);
            String username = map.get("username").toString();
            String eid = map.get("eId").toString();
            String name = map.get("eName").toString();
            if (userService.checkAccount(username) > 0 || userService.checkEid(eid) > 0 || userService.checkName(name) > 0) {
                return new JsonResult<>(Result.CODE_FAILURE, Result.MESSAGE_FAILURE, "该用户已存在，不能重复注册");
            }
            String password = map.get("password").toString();
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            Employee employee = new Employee();
            employee.setEId(map.get("eId").toString());
            employee.setEName(map.get("eName").toString());
            employee.setEIdnum(map.get("eIdnum").toString());
            employee.setEWages((Integer) map.get("eWages"));
            if (userService.addUser(user, employee) > 0) {
                return new JsonResult<>(Result.CODE_SUCCESS, Result.MESSAGE_SUCCESS, "注册成功");
            } else {
                return new JsonResult<>(Result.CODE_FAILURE, Result.MESSAGE_FAILURE, "注册失败");
            }
        } catch (Exception e) {
            return new JsonResult<>(Result.CODE_ERROR, Result.MESSAGE_ERROR, "注册失败，异常信息=" + e.getMessage());
        }

    }

    //用户上传头像方法
    @PostMapping(value = "/public/upload", produces = "application/json;charset=UTF-8")
    public JsonResult<String> upload(@RequestParam("username") String username,
                                     @RequestParam("fileList") MultipartFile file) {
        //获取文件名
        String fileName = file.getOriginalFilename();
        //获取文件后缀
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //生成uuid
        UUID uuid = UUID.randomUUID();
        //获取文件存储的物理路径
        String dstFilePath = Constant.FILE_UPLOAD_DIR + "images/" + uuid + suffixName;
        //文件在数据库钟存储的值（相对路径，定义为images路径下）
        String avatarUrl = "\\images\\" + uuid + suffixName;
        //创建文件
        File picFile = new File(dstFilePath);
        //如果文件所在的文件夹不存在，先创建文件夹
        if (!picFile.getParentFile().exists()) {
            picFile.getParentFile().mkdirs();
        }
        try {
            //保存文件
            file.transferTo(picFile);
            // 更新用户头像
            if (userService.setAvatar(avatarUrl, username) > 0) {
                return new JsonResult<String>(200, "头像上传成功", avatarUrl);
            } else {
                return new JsonResult<String>(200, "头像更新失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult<String>(500, "头像上传失败");
        }
    }

}
