package com.example.work_test.dao;

import com.example.work_test.pojo.test;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class testDaoTest {
    @Autowired
    testDao testDao;
    @Test
    void add() {
        test t = new test();
        t.id = 32;
        t.name = "Lish";
        if (testDao.add(t)) {
            System.out.println("执行成功");
        }else {
            System.out.println("执行失败");
        }
    }

    @Test
    void findAll() {
        testDao.findAll();
    }
}