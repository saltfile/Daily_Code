package com.saltfish.expamle.Contoller;

import com.alibaba.nacos.shaded.com.google.gson.Gson;
import com.saltfish.expamle.pojo.Admin;
import com.saltfish.expamle.service.DemoService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.repository.support.SimpleJdbcRepository;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @Autowired
    DemoService demoService;

    @GetMapping(value = "/get")
    public Object getAllUsers(String id, String name)  {
        return demoService.Test(id, name);
    }

    @GetMapping(value = "/get1")
    public Object getAllUsers1(String id, String name)  {
        return demoService.Test1(id, name);
    }
    @GetMapping(value = "/g")
    public Object get(String id,String name)
    {
        Admin a = new Admin();
        a.id = id;
        a.name = name;
        Gson gson = new Gson();

        return gson.toJson(a);
    }

}
