package com.project.lbms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.lbms.service.CartService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1")
public class CartController {
    private CartService cartService;
    private static final String CART_CONTROLLER_STR = "CartController";

    public CartController(CartService cartService){
        this.cartService = cartService;
    }
}
