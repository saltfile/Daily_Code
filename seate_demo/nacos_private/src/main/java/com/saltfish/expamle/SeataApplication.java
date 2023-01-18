package com.saltfish.expamle;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication //开启基于注解的dubbo功能
@EnableDiscoveryClient
public class SeataApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeataApplication.class,args);
    }
}
