package com.example.demo.service;

import com.example.demo.enitys.user;

public interface userService {
    public int register(user u);

    public user login(String id,String password);

    public int del(String id,String password);
}
