package com.example.jwt_login.service;

import com.example.jwt_login.mappers.ProductMapper;
import com.example.jwt_login.pojo.BillRecord;
import com.example.jwt_login.pojo.Product;
import com.example.jwt_login.pojo.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ProductService{
    @Autowired
    private ProductMapper productMapper;


    public List<Product> findAll(){
        return productMapper.findAll();
    }

    public int addProduct(Product product){

        if(productMapper.addProduct(product)>0){
            return productMapper.updateInventory(product);
        }

        else{
            return 0;
        }
    }

    public int saleProduct(Map<String,Object> map){
        if (productMapper.getProductCount(map)>=(Integer) map.get("pCount")){
            productMapper.saleRecord(map);
            return productMapper.saleProduct(map);
        }else {
            return 0;
        }
    }

    public List<BillRecord> inbillRecord(Map<String,Object> map){
        return productMapper.inbillRecord(map);
    }
    public List<BillRecord> outbillRecord(Map<String,Object> map){
        return productMapper.outbillRecord(map);
    }

    public int modifyProduct(Product product){
        return productMapper.modifyProduct(product);
    }
    public List<Record> getInRecord(){
        return productMapper.getInRecord();
    }
    public List<Record> getSaleRecord(){
        return productMapper.getSaleRecord();
    }

}
