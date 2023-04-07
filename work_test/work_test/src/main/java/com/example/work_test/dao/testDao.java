package com.example.work_test.dao;

import com.example.work_test.pojo.test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class testDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    public boolean add(test t){
       return mongoTemplate.save(t)!=null;
    }

    public void findAll(){
        List<test> arr = mongoTemplate.findAll(test.class);
        for (test a:arr) {
            System.out.println(a.id+"  "+a.name);
        }
    }



}
