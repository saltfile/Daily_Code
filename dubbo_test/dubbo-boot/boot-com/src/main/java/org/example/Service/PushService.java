package org.example.Service;

import org.apache.dubbo.config.annotation.DubboReference;
import org.example.ToDoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PushService implements CommandLineRunner {

    @DubboReference
    ToDoService service;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("任务开始");
        System.out.println(service.Add(1,4));
        System.out.println(service.Sub(45,4));

    }
}
