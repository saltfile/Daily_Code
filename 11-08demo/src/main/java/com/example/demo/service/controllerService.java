package com.example.demo.service;

import com.example.demo.entiry.contorller;
//dao层接口
public interface controllerService {

    public contorller FindByName_Pass(String name,String password);

    public int Add(contorller con);

}
