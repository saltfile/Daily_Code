package com.saltfish.expamle.service;

import com.saltfish.expamle.dao.AdminDao;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DemoService {
    @Autowired
    AdminDao adminDao;

    @GlobalTransactional(rollbackFor = Exception.class)
    @Transactional(rollbackFor = Exception.class)   //本地方法事务
    public String Test(String id,String name){
        int num = adminDao.Add(id, name);
        if (num > 0)
        System.out.println(" 添加成功 ");
        if (num > 0)
        return "{Hello World}";
        else
            return "{fail}";
    }
    @GlobalTransactional(rollbackFor = Exception.class)
    @Transactional(rollbackFor = Exception.class)   //本地方法事务
    public String Test1(String id,String name){
        int num = adminDao.Add(id, name);
        if (num > 0)
            System.out.println(" 添加成功 ");
        int a = 1/0;
        if (num > 0)
            return "{Hello World}";
        else
            return "{fail}";
    }
}
