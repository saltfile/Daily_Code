package com.example.demo.service;


import com.example.demo.dao.contotllerDao;
import com.example.demo.entiry.contorller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * TODO:这里的是业务层，由于目前业务单一所以只是调用dao层而已
 * */
@Service
public class controllerServicempl{


    @Autowired
    private contotllerDao contotllerDao;
    //登录
    public contorller LoginByNaPass(String ctr_id,String ctr_password){
        return contotllerDao.FindByName_Pass(ctr_id,ctr_password);
    }
    //注册
    public int ReitsyAdmin(contorller con){
        return contotllerDao.Add(con);
    }

}



