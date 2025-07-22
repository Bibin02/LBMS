package com.project.lbms.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.lbms.exception.LbmsException;
import com.project.lbms.service.OrderService;
import com.project.lbms.util.LbmsResponseEntityBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/")
public class OrderController extends LbmsResponseEntityBuilder{
    private OrderService orderService;
    private static final String ORDER_CONTROLLER_STR = "OrderController";

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping(path = "/order/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUserOrder(
        @PathVariable String orderId
    ) throws LbmsException{
        log.info("{} getUserOrder {}", ORDER_CONTROLLER_STR, orderId);
        return getResponseEntityOk(orderService.getOrder(orderId));
    }

    @GetMapping(path = "/orders/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUserOrders(
        @PathVariable String userId
    ) throws LbmsException{
        log.info("{} getUserOrder {}", ORDER_CONTROLLER_STR, userId);
        return getResponseEntityOk(orderService.getUserOrders(userId));
    }
}
