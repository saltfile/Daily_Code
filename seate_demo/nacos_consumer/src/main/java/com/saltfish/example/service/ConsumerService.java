package com.saltfish.example.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("demo-private")
public interface ConsumerService {
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    String consumer(String id, String name);






}
