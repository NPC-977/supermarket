package com.example.jwt_login.controller;

import com.example.jwt_login.config.Result;
import com.example.jwt_login.pojo.ClockRecord;
import com.example.jwt_login.pojo.Employee;
import com.example.jwt_login.pojo.JsonResult;
import com.example.jwt_login.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/admin/employee")
    public JsonResult<List<Employee>> findAll(){
        return new JsonResult<>(Result.CODE_SUCCESS,"success",employeeService.findAll());
    }

    @PostMapping("employee/clock")
    public JsonResult<String> employeeClock(@RequestBody String jsonString){
        JsonParser jsonParser = JsonParserFactory.getJsonParser();
        Map<String, Object> map = jsonParser.parseMap(jsonString);
        if(map.containsKey("eId")){
            if(employeeService.employeeClock(map.get("eId").toString())>0){
                return new JsonResult<>(Result.CODE_SUCCESS,Result.MESSAGE_SUCCESS,"打卡成功");
            }else {
                return new JsonResult<>(Result.CODE_FAILURE,Result.MESSAGE_FAILURE,"打卡失败");
            }
        }
        return new JsonResult<>(Result.CODE_FAILURE,Result.MESSAGE_FAILURE,"参数错误");
    }

    @GetMapping("admin/clockrecord")
    public JsonResult<List<ClockRecord>> employeeClockRecord(){
        List<ClockRecord> clockRecordList=employeeService.employeeClockRecord();
        if (clockRecordList.size()>0){
            return new JsonResult<>(Result.CODE_SUCCESS,Result.MESSAGE_SUCCESS,clockRecordList);
        }else {
            return new JsonResult<>(Result.CODE_SUCCESS,"记录为空");
        }
    }

    @PostMapping("admin/modifyemployee")
    public JsonResult<String> modifyEmployee(@RequestBody String jsonString){
        JsonParser jsonParser = JsonParserFactory.getJsonParser();
        Map<String, Object> map = jsonParser.parseMap(jsonString);
        Employee employee=new Employee();
        if(map.containsKey("eid") && map.containsKey("ename") && map.containsKey("eidnum") && map.containsKey("ewages")){
            employee.setEId(map.get("eid").toString());
            employee.setEName(map.get("ename").toString());
            employee.setEIdnum(map.get("eidnum").toString());
            employee.setEWages(Float.parseFloat(map.get("ewages").toString()));
            if(employeeService.modifyEmployee(employee)>0){
                return new JsonResult<>(Result.CODE_SUCCESS,Result.MESSAGE_SUCCESS,"修改成功");
            }else {
                return new JsonResult<>(Result.CODE_FAILURE,Result.MESSAGE_FAILURE,"修改失败");
            }
        }else return new JsonResult<>(Result.CODE_FAILURE,Result.MESSAGE_FAILURE,"参数错误");
    }
    @GetMapping("admin/deleteemployee/{id}")
    public JsonResult<String> deleteEmployee(@PathVariable String id){
        if (employeeService.deleteEmployee(id)){
            return new JsonResult<>(Result.CODE_SUCCESS,Result.MESSAGE_SUCCESS,"删除成功");
        }else return new JsonResult<>(Result.CODE_FAILURE,Result.MESSAGE_FAILURE,"删除失败");
    }
    @GetMapping("employee/getemployeeid/{username}")
    public JsonResult<String> getEmployeeId(@PathVariable String username){
        return  new JsonResult<>(Result.CODE_SUCCESS,Result.MESSAGE_SUCCESS,employeeService.getEmployeeId(username));
    }
}

