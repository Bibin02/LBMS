package com.project.lbms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.lbms.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1")
public class OrderController {
    private OrderService orderService;
    private static final String ORDER_CONTROLLER_STR = "OrderController";

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }
}
