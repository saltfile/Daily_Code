package com.example.k1demo.service;

import com.example.k1demo.dao.GoodsMapper;
import com.example.k1demo.dao.OtherMapper;
import com.example.k1demo.dao.SnapshotMapper;
import com.example.k1demo.dao.UserMapper;
import com.example.k1demo.entiry.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateOtherService {
    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    OtherMapper otherMapper;

    @Autowired
    SnapshotMapper snapshotMapper;

    @Autowired
    UserMapper userMapper;

    public String OrtherShen(int uid,String gname){
        //先去redis查看此用户是否在







    }











}
