package com.example.jwt_login.mappers;

import com.example.jwt_login.pojo.UserRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleMapper {
    int addUserRole(int userId,int roleId);
    UserRole findUserRoleIdByUserId(int userId, int roleId);
}
