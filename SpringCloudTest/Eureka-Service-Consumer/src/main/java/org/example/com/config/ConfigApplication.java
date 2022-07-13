package org.example.com.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigApplication {
    @Bean
    public RandomRule randomRule() {
        return new RandomRule();
    }
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
