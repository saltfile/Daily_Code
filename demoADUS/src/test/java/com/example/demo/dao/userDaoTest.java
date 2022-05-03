package com.example.demo.dao;

import com.example.demo.entiry.user_Tab;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class userDaoTest {
    @Autowired
    userDao userDao;
    @Test
    void add() {
        user_Tab user = new user_Tab();
        user.setId("Admin1111");
        user.setPassword("123456");
        user.setUsername("admin124");
        log.info(user.getUsername());
        user.setJurs(2);
        if(userDao.Add(user) > 0)
        log.info("add();执行成功");
    }

    @Test
    void del() {
        if(userDao.Del("138xxxxxxxxxxx") > 0){
            log.info("执行成功");
        }
    }

    @Test
    void update() {
        user_Tab user = new user_Tab();
        user.setId("138xxxxxxxxxxx");
        user.setPassword("123456");
        user.setUsername("userasfdsfgfdg");
        log.info(user.getUsername());
        if(userDao.Update(user) > 0)
            log.info("修改成功");
    }

    @Test
    void sel() {
        user_Tab user = userDao.Sel("138xxxxxxxxxxx","138xxxxxxxxxxx");
        assertNotNull(user);
    }

    @Test
    void showAll() {
        ArrayList<user_Tab> users = userDao.ShowAll();
        if(users.size() >= 1)log.info("找到");
    }
}