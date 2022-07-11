package org.example.com.service;

import org.example.com.enitry.Order;
import org.example.com.enitry.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderServicempl implements OrderService{
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    DiscoveryClient discoveryClient;
    @Override
    public Order selectOrderById(Integer id) {

        return new Order(id,"order-001","中国",31994D,ProductselectByDisCoverClient());
    }
    private List<Product> ProductselectByDisCoverClient(){
        return null;
    }
}
