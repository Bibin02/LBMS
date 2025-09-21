package com.project.lbms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.lbms.dto.CartUpdateRequest;
import com.project.lbms.service.CartService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/cart")
public class CartController{
    private CartService cartService;
    private static final String CART_CONTROLLER_STR = "CartController";

    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<Object> getUserCart(
        @PathVariable String userId
    ) throws Exception{
        log.info("{} getUserCart {}", CART_CONTROLLER_STR, userId);
        return cartService.getUserCart(userId);
    }

    @GetMapping(path = "/{cartId}")
    public ResponseEntity<Object> getCart(
        @PathVariable String cartId
    ) throws Exception{
        log.info("{} getCart {}", CART_CONTROLLER_STR, cartId);
        return cartService.getUserCart(cartId);
    }

    @PutMapping(path = "/{userId}")
    public ResponseEntity<Object> updateUserCart(
        @PathVariable String userId,
        @Valid @RequestBody CartUpdateRequest cartDto
    ) throws Exception{
        log.info("{} updateUserCart {}", CART_CONTROLLER_STR, userId);
        return cartService.updateUserCart(userId, cartDto);
    }
}
