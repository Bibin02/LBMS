package com.project.lbms.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.lbms.exception.LbmsException;
import com.project.lbms.service.OrderService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/order")
@Tag(name = "Order Controller", description = "Endpoints related to orders")
public class OrderController{
    private OrderService orderService;
    private static final String ORDER_CONTROLLER_STR = "OrderController";

    @GetMapping(path = "/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUserOrder(
        @PathVariable String orderId
    ) throws LbmsException{
        log.info("{} getUserOrder {}", ORDER_CONTROLLER_STR, orderId);
        return ResponseEntity.ok((orderService.getOrder(orderId)));
    }

    @GetMapping(path = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUserOrders(
        // Decrypted token will have userId and role filtered based on role via method level security
        @PathVariable String userId,
        @RequestParam(required = false, defaultValue = "0") Integer pageNumber
    ) throws LbmsException{
        log.info("{} getUserOrder {} page number {}", ORDER_CONTROLLER_STR, userId, pageNumber);
        return ResponseEntity.ok((orderService.getUserOrders(userId, pageNumber)));
    }

    @PostMapping(path = "/user/{userId}/{cartId}", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Object> placeOrder(
        // Decrypted token will have userId and role filtered based on role via method level security
        @PathVariable String userId,
        @PathVariable String cartId
    ) throws LbmsException{
        log.info("{} placeOrder for user {} for cart {}", ORDER_CONTROLLER_STR, userId, cartId);
        return orderService.placeOrder(userId, cartId);
    }
}
