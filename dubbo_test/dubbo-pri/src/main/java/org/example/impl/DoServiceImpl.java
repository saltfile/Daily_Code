package org.example.impl;

import org.example.api.DoService;

import java.io.DataOutput;

public class DoServiceImpl implements DoService {
    public Integer SayHello(String name) {
        System.out.println("hello !! "+name);
        return name.length();
    }
}
