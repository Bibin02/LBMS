package com.project.lbms.service;

import org.springframework.stereotype.Service;

import com.project.lbms.repository.CartRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CartService {
    private CartRepository cartRepository;
    private static final String CART_SERVICE_STR = "CartService";

    public CartService(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }
}
