package com.example.jwt_login.mappers;

import com.example.jwt_login.pojo.BillRecord;
import com.example.jwt_login.pojo.ClockRecord;
import com.example.jwt_login.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface EmployeeMapper {
    List<Employee> findAll();
    int employeeClock(String pId);
    List<ClockRecord> employeeClockRecord();

    int addEmployee(Employee employee);

    int modifyEmployee(Employee employee);
    String getUrid(String id);
    String getUid(String uid);
    int deleteUser(String uid);
    int deleteUserRole(String urid);
    int deleteEmployee(String id);
    String getEmployeeId(String username);
}
