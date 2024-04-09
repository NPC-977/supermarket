package com.example.jwt_login.controller;

import com.example.jwt_login.config.Result;
import com.example.jwt_login.mappers.ProductMapper;
import com.example.jwt_login.pojo.BillRecord;
import com.example.jwt_login.pojo.JsonResult;
import com.example.jwt_login.pojo.Product;
import com.example.jwt_login.pojo.Record;
import com.example.jwt_login.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/public/inventory")//获取库存信息
    public JsonResult<List<Product>> findAll(){
        return new JsonResult<>(Result.CODE_SUCCESS,"success",productService.findAll());
    }

    @PostMapping("/admin/inventoryadd")//入库
    public JsonResult<String> addProduct(@RequestBody String jsonString){
        JsonParser jsonParser = JsonParserFactory.getJsonParser();
        Map<String, Object> map = jsonParser.parseMap(jsonString);
        if(map.containsKey("pId") && map.containsKey("pName") && map.containsKey("pCount") && map.containsKey("pPrice") && map.containsKey("pSellprice")){
            Product product=new Product();
            product.setPId(map.get("pId").toString());
            product.setPName(map.get("pName").toString());
            product.setPCount((Integer) map.get("pCount"));
            product.setPPrice(Float.parseFloat(map.get("pPrice").toString()));
            product.setPSellprice(Float.parseFloat(map.get("pSellprice").toString()) );
            if(productService.addProduct(product)>0){
                return new JsonResult<>(Result.CODE_SUCCESS,Result.MESSAGE_SUCCESS, "入库成功");
            }else {
                return new JsonResult<>(Result.CODE_FAILURE,Result.MESSAGE_FAILURE, "入库失败");
            }
        }
        return new JsonResult<>(Result.CODE_FAILURE,Result.MESSAGE_FAILURE, "参数错误");
    }

    @PostMapping("/employee/sale")//出售
    public JsonResult<String> saleProduct(@RequestBody String jsonString){
        JsonParser jsonParser = JsonParserFactory.getJsonParser();
        Map<String, Object> map = jsonParser.parseMap(jsonString);
        if(map.containsKey("pId") && map.containsKey("pCount")){
            if(productService.saleProduct(map)>0){
                System.out.println("出售");
                return new JsonResult<>(Result.CODE_SUCCESS,Result.MESSAGE_SUCCESS,"出售成功");
            }else {
                return new JsonResult<>(Result.CODE_FAILURE,Result.MESSAGE_FAILURE, "库存不足");
            }
        }
        return new JsonResult<>(Result.CODE_FAILURE,Result.MESSAGE_FAILURE, "参数错误");
    }

    @PostMapping("/admin/inbillrecord")//收入记录
    public JsonResult<List<BillRecord>> inbillRecord(@RequestBody String jsonString){
        JsonParser jsonParser = JsonParserFactory.getJsonParser();
        Map<String, Object> map = jsonParser.parseMap(jsonString);
        if (map.containsKey("billYear") && map.containsKey("billMonth")){
            List<BillRecord> result=productService.inbillRecord(map);
            return new JsonResult<>(Result.CODE_SUCCESS,Result.MESSAGE_SUCCESS,result);
        }
        return new JsonResult<>(Result.CODE_FAILURE,Result.MESSAGE_FAILURE);
    }

    @PostMapping("/admin/outbillrecord")
    public JsonResult<List<BillRecord>> outbillRecord(@RequestBody String jsonString){
        JsonParser jsonParser = JsonParserFactory.getJsonParser();
        Map<String, Object> map = jsonParser.parseMap(jsonString);
        if (map.containsKey("billYear") && map.containsKey("billMonth")){
            List<BillRecord> result=productService.outbillRecord(map);
            return new JsonResult<>(Result.CODE_SUCCESS,Result.MESSAGE_SUCCESS,result);
        }
        return new JsonResult<>(Result.CODE_FAILURE,Result.MESSAGE_FAILURE);
    }

    @PostMapping("/admin/modifyproduct")
    public JsonResult<String> modifyProduct(@RequestBody String jsonString){
        JsonParser jsonParser = JsonParserFactory.getJsonParser();
        Map<String, Object> map = jsonParser.parseMap(jsonString);
        System.out.println(map);
        if (map.containsKey("id") && map.containsKey("pcount") && map.containsKey("pname") && map.containsKey("pid") && map.containsKey("psellprice") && map.containsKey("pprice")){
            Product product=new Product();
            product.setId(Integer.parseInt(map.get("id").toString()));
            product.setPName(map.get("pname").toString());
            product.setPCount(Integer.parseInt(map.get("pcount").toString()));
            product.setPId(map.get("pid").toString());
            product.setPSellprice(Float.parseFloat(map.get("psellprice").toString()));
            product.setPPrice(Float.parseFloat(map.get("pprice").toString()));
            if (productService.modifyProduct(product)>0){
                return new JsonResult<>(Result.CODE_SUCCESS,Result.MESSAGE_SUCCESS,"修改成功");
            }else return new JsonResult<>(Result.CODE_FAILURE,Result.MESSAGE_FAILURE,"修改失败");
        }
        return new JsonResult<>(Result.CODE_FAILURE,Result.MESSAGE_FAILURE,"参数错误");
    }

    @GetMapping("/admin/inrecord")
    public JsonResult<List<Record>> getInRecord(){
        return new JsonResult<>(Result.CODE_SUCCESS,Result.MESSAGE_SUCCESS,productService.getInRecord());
    }

    @GetMapping("/admin/salerecord")
    public JsonResult<List<Record>> getSaleRecord(){
        return new JsonResult<>(Result.CODE_SUCCESS,Result.MESSAGE_SUCCESS,productService.getSaleRecord());
    }
}
