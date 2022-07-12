package org.example.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ServiceConsumer03Application {
    public static void main(String[] args) {
        SpringApplication.run(ServiceConsumer03Application.class,args);
    }
}
