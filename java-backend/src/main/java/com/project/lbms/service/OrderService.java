package com.project.lbms.service;

import org.springframework.stereotype.Service;

import com.project.lbms.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderService {
    private OrderRepository orderRepository;
    private static final String ORDER_SERVICE_STR = "OrderService";

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }
}
