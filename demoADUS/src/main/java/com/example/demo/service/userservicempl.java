package com.example.demo.service;

import com.example.demo.entiry.user_Tab;

import java.util.ArrayList;

public interface userservicempl {
    user_Tab Login(String id, String password);
    boolean Register(user_Tab user);
    ArrayList<user_Tab> Show_All();
    boolean Delete(String id);
    boolean Update(user_Tab user);
}
