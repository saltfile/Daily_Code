package com.example.demo;

import com.example.demo.dao.userDao;
import com.example.demo.enitys.user;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {


    @Autowired
    public userDao userDao;
    @Test
    void contextLoads() {
    }
    @Test
    void Test1(){
        user u = new user();
        u.setId("13939115105");
        u.setPassword("ass");
        u.setName("xxx");

        System.out.println(userDao.register(u));


    }
    @Test
    void Test2(){
        user user = userDao.login("13939115105","ass");
        if(user!= null){
            System.out.println("登陆成功");
        }
    }
    @Test
    void Test3(){
       int a = userDao.del("13939115105","ass");
        System.out.println(a);
       if(a > 0){
            System.out.println("删除成功");
        }


    }

}
