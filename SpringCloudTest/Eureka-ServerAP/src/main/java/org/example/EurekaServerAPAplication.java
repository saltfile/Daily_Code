package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServerAPAplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerAPAplication.class,args);
    }
}
