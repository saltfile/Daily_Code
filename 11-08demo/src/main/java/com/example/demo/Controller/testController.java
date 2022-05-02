package com.example.demo.Controller;

import com.example.demo.entiry.contorller;
import com.example.demo.entiry.park;
import com.example.demo.service.ParkServicelmpl;
import com.example.demo.service.controllerServicempl;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * TODO:这里仅有两个网页的url链接
 * */
@Controller
public class testController {

    //这里是park部分的事物
    @Autowired
    public ParkServicelmpl parkServicelmpl;
    //这里是管理员登录注册业务
    @Autowired
    public controllerServicempl Conservmpl;
    /**
     * @param /test是park部分做的表信息网页
     * @return 返回主要的页面
     * */
    @RequestMapping("/test")
    public String index(){
        return  "/table.html";
    }


    /**
     * TODO:将所有数据以json格式发到tablr.html的页面上
     * @return 以json格式的park表中的所有数据
     * */
    @RequestMapping(value = "/reg")
    @ResponseBody
    public String test2(){
        //gson是可以将类转换成json格式
        Gson gson = new Gson();
        //层事物拿到数据集
        ArrayList<park> arr = parkServicelmpl.AllTableDate();
        //转换成json
        String str ="{\"code\":0,\"msg\":\"\",\"count\":1,\"data\":"+gson.toJson(arr)+"}";
        return str;
    }

    /**
     * TODO:通过选取表上的某一行数据进行删除
     * @param data 网页上某一行的json格式
     * */
    @RequestMapping(value = "/del")
    @ResponseBody
    public String test3(String data){
        //gson是可以将类转换成json格式
        Gson gson = new Gson();
        String str = "";
        //转换json
        park p = gson.fromJson(data,park.class);
        //查找到相应的数据并删除
        str += parkServicelmpl.DeleteParkByObj(p);
        return str;
    }

    /**
     * TODO:URL展示login_page.html
     * */
    @RequestMapping("/logins")
    public String logins(){ return "/login_page.html";}



    /**
     * TODO:具体登录业务
     * @param name：是账号
     * @param password:是密码
     * @return 验证登录是否成功
     * */
    @RequestMapping(value = "/lon")
    @ResponseBody
    public String login(String name,String password){
//        System.out.println(name+"       "+password);
        //调用登录业务
        contorller c = Conservmpl.LoginByNaPass(name,password);
        //如果是Y就说明验证成功,如果是N就是没有登陆成功（这里可以更加细化）
       if( c.getCtr_id() != null){
           return "Y";
       }
        return "N";
    }




}
