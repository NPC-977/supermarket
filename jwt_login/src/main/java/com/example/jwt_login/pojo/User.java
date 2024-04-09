package com.example.jwt_login.pojo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用户对象，需要实现UserDetails接口
 */
@Data
public class User implements UserDetails {

    private int id;
    private String username;
    private String password;
    //账号是否过期
    private int isAccountNonExpired;
    //账号是否锁定
    private int isAccountNonLocked;
    //密码是否过期
    private int isCredentialsNonExpired;
    //用户是否启用
    private int isEnabled;
    //用户权限列表
    private List<Role> roleList;
    //头像
    private String avatar;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roleList) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired==0?true:false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked==0?true:false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired==0?true:false;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled==0?true:false;
    }
}
