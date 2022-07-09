package com.saltfish.cloud.dao;

import com.saltfish.cloud.entities.payment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PaymentDaoTest {
    @Resource
    PaymentDao paymentDao;
    @Test
    void add() {
        paymentDao.Add("sss002","哈哈哈");
    }

    @Test
    void sel() {
        payment paymen = paymentDao.Sel((long)4);
        System.out.println(paymen.getContent());
    }
}