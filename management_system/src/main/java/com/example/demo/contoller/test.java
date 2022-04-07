package com.example.demo.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class test {
    @RequestMapping("/404")
    public String STest(){
        return "/404.html";
    }

    @RequestMapping("/login")
    public String login(){
        return "/lon.html";
    }









}
