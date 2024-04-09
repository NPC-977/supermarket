package com.example.jwt_login.service;

import com.example.jwt_login.mappers.EmployeeMapper;
import com.example.jwt_login.pojo.ClockRecord;
import com.example.jwt_login.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    public List<Employee> findAll(){
        return employeeMapper.findAll();
    }

    public int employeeClock(String pId){
        return employeeMapper.employeeClock(pId);
    }

    public List<ClockRecord> employeeClockRecord(){
        return employeeMapper.employeeClockRecord();
    }
    public int modifyEmployee(Employee employee){
        return employeeMapper.modifyEmployee(employee);
    }
    public boolean deleteEmployee(String id){
        String urid=employeeMapper.getUrid(id);
        String uid=employeeMapper.getUid(urid);
        return employeeMapper.deleteUser(uid) > 0 && employeeMapper.deleteUserRole(urid) > 0 && employeeMapper.deleteEmployee(id) > 0;
    }
    public String getEmployeeId(String usernaem){
        return employeeMapper.getEmployeeId(usernaem);
    }
}
