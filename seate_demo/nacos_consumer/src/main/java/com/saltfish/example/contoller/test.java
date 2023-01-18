package com.saltfish.example.contoller;

import com.saltfish.example.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class test {
    @Autowired
    ConsumerService service;
    @GetMapping("/con")
    public String echow(String id,String name) {
        return "{"+id+name+"}";
    }



    @GetMapping("/consumer")
    public String echo(String id,String name) {
        return service.consumer(id, name);
    }
}
