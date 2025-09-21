package com.project.lbms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.lbms.dto.CartUpdateRequest;
import com.project.lbms.exception.LbmsException;
import com.project.lbms.service.CartService;
import com.project.lbms.util.LbmsResponseEntityBuilder;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1")
public class CartController extends LbmsResponseEntityBuilder{
    private CartService cartService;
    private static final String CART_CONTROLLER_STR = "CartController";

    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @GetMapping(path = "/cart/{userId}")
    public ResponseEntity<Object> getUserCart(
        @PathVariable String userId
    ) throws LbmsException{
        log.info("{} getUserCart {}", CART_CONTROLLER_STR, userId);
        return getResponseEntityOk(cartService.getUserCart(userId));
    }

    @PutMapping(path = "/cart/{userId}")
    public ResponseEntity<Object> updateUserCart(
        @PathVariable String userId,
        @Valid @RequestBody CartUpdateRequest cartDto
    ) throws Exception{
        log.info("{} updateUserCart {}", CART_CONTROLLER_STR, userId);
        return cartService.updateUserCart(userId, cartDto);
    }
}
