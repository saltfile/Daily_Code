package com.saltfish.cloud.contoller;

import com.saltfish.cloud.entities.CommResult;
import com.saltfish.cloud.entities.payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class TestController {
    public static final String PAYMENT_URL = "http://localhost:8001";

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("customer/payment/create")
    public CommResult<payment> create (payment payment){
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommResult.class);
    }

    @GetMapping("customer/payment/{id}")
    public CommResult<payment> getPaymentById(@PathVariable("id")Long id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/" + id, CommResult.class);
    }
}
