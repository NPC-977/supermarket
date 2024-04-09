package com.example.jwt_login.service;


import com.example.jwt_login.mappers.EmployeeMapper;
import com.example.jwt_login.mappers.UserMapper;
import com.example.jwt_login.mappers.UserRoleMapper;
import com.example.jwt_login.pojo.Employee;
import com.example.jwt_login.pojo.User;
import com.example.jwt_login.pojo.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional   //事务支持
public class UserService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    /**
     * 根据用户名加载用户，若找不到抛出UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userMapper.findUserByUsername(username);
        if (user!=null){
            return user;
        }else{
            throw new UsernameNotFoundException("该用户不存在");
        }

    }
    /**
     * 检查用户名是否存在
     */
    public int checkAccount(String username){
        return userMapper.checkAccount(username);
    }
    public int checkEid(String eid){
        return userMapper.checkEid(eid);
    }
    public int checkName(String name){
        return userMapper.checkName(name);
    }

    /**
     * 先添加用户，再添加用户角色
     */
    public int addUser(User user, Employee employee){
        String encodePassword =  new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodePassword);
        //1 添加用户
        if (userMapper.addUser(user) > 0) {
            //2 添加用户角色
            User u = userMapper.findUserByUsername(user.getUsername());
            System.out.println(u);
            if( userRoleMapper.addUserRole(u.getId(), 2)>0){
                //3 添加学生
                UserRole userRole = userRoleMapper.findUserRoleIdByUserId(u.getId(),2);
                employee.setEURid(userRole.getId());
//                System.out.println(userRole);
                System.out.println(employee);
                System.out.println("here");

                return employeeMapper.addEmployee(employee);
            }
        }
        return 0;
    }
    public int setAvatar(String avatar,String username){
        return userMapper.setAvatar(avatar,username);
    }
}
