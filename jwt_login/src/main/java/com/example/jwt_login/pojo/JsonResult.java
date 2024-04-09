package com.example.jwt_login.pojo;

import lombok.Data;
//定义统一的JSON结构
@Data
public class JsonResult<T> {
    //状态码
    private int code;
    //消息
    private String msg;
    //数据，此处T为泛型
    private T data;
    //定义2个构造函数，分别对应有无data的情况
    public JsonResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public JsonResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
