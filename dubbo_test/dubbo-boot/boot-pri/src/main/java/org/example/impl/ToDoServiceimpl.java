package org.example.impl;

import org.apache.dubbo.config.annotation.DubboService;
import org.example.ToDoService;
@DubboService
public class ToDoServiceimpl implements ToDoService {
    @Override
    public int Add(int a, int b) {
        System.out.println("Add "+a+" + "+b);
        return a+b;
    }

    @Override
    public int Sub(int a, int b) {
        System.out.println("Sub "+a+" - "+b);
        return a-b;
    }
}
