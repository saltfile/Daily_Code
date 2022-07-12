package org.example.com.service;

import org.example.com.enitry.Order;

public interface OrderService {
    Order selectOrderById(Integer id);
}
