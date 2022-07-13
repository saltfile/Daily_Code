package org.example.com.service;

import org.example.com.enitry.Order;
import org.example.com.enitry.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderServicempl implements OrderService{
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Override
    public Order selectOrderById(Integer id) {
        return new Order(id,"order-001","中国",31994D,ProductselectByDisCoverClient());
    }

    private List<Product> ProductselectByDisCoverClient(){
        StringBuffer sb = null;

        ServiceInstance si = loadBalancerClient.choose("");
        if (si == null)
            return null;

        sb = new StringBuffer();
        sb.append("http://"+si.getHost()+":"+si.getPort()+"/product/list");

        ResponseEntity<List<Product>> response = restTemplate.exchange(
                sb.toString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {});

        return response.getBody();
    }
}
