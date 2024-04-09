package com.example.jwt_login.mappers;

import com.example.jwt_login.pojo.BillRecord;
import com.example.jwt_login.pojo.Product;
import com.example.jwt_login.pojo.Record;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {
    List<Product> findAll();
    int addProduct(Product product);
    int updateInventory(Product product);
    int saleProduct(Map<String,Object> map);
    int getProductCount(Map<String,Object> map);
    int saleRecord(Map<String,Object> map);
    List<BillRecord> inbillRecord(Map<String,Object> map);
    List<BillRecord> outbillRecord(Map<String,Object> map);
    int modifyProduct(Product product);

    List<Record> getInRecord();
    List<Record> getSaleRecord();
}
