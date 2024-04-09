package com.example.jwt_login.pojo;

import lombok.Data;

@Data
public class BillRecord {
    private String billYear;
    private String billMonth;
    private float billMoney;
}
