package com.example.demo;

import com.example.demo.dao.contotllerDao;
import com.example.demo.dao.parkDao;
import com.example.demo.entiry.contorller;
import com.example.demo.entiry.park;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private parkDao parkDao;
    @Autowired
    private contotllerDao contotllerDao;
    @Test
    void contextLoads() {
        park Part = new park("004","this is park4","a",625,556,545);
        int i = parkDao.Add(Part);
        if(i >= 0){
        System.out.println("已增加一条信息"+i);
        }
    }

    @Test
    void test2(){
        String parkid = "002";
        int i = parkDao.Del(parkid);
        if(i < 0){
            System.out.println("未找到该地区");
        }else{
            System.out.println("执行成功"+i);
        }

    }

    @Test
    void test3(){
        Gson gson = new Gson();
      ArrayList<park> arr = parkDao.AllTable();
      for(int i = 0;i < arr.size();i++){
          System.out.println("That is a park_id:"+arr.get(i).getPark_id());
      }
      String gsons = gson.toJson(arr);
        System.out.println(gsons);

    }
    @Test
    void test4(){
        park park = new park("003","this is park3","b",65,56,545);

        parkDao.DelByObj(park);
    }

    @Test
    void test5(){
        contorller c = contotllerDao.FindByName_Pass("13978545684","123456");

        if(c.getCtr_id() != null){
            System.out.println("已找到账号:"+c.getCtr_id());
        }

    }

    @Test
    void test6(){
        contorller d = new contorller();
        d.setCtr_id("13978545684");
        d.setCtr_password("123456");
        contotllerDao.Add(d);
    }




}
