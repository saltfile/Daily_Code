package com.example.demo.contoller;

import com.example.demo.entiry.user_Tab;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
@Slf4j
@Controller
public class UserContoller {
    /**
     * 用户业务
     */
    @Autowired
    UserService userService;



    /**
     * TODO:登录页面的url
     * @return
     */
    @RequestMapping("/login")
    public String login(){
        return  "/Login.html";
    }



    /**
     * TODO:登录业务接口
     * @return 验证是否成功
     */
    @RequestMapping(value = "/Lon")
    @ResponseBody
    public String Lon(String id,String password){
        log.info("从前端获取"+id+"  "+password);
        //获取用户信息
        user_Tab user = userService.Login(id,password);
        System.out.println(user.getId());
        //如果user不是空的就证明用户存在
        if(user != null && user.getJurs() == 1)return "Y";//普通用户
        if(user != null && user.getJurs() == 2)return "AY";//管理员
        return "";
    }



}
