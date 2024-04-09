package com.example.jwt_login.mappers;

import com.example.jwt_login.pojo.User;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface UserMapper {
    User findUserByUsername(String username);
    int addUser(User user);
    int checkAccount(String username);
    int checkEid(String eid);
    int checkName(String name);
    int setAvatar(String avatar,String name);
}
