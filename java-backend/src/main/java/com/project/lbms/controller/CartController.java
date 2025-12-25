package com.project.lbms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.lbms.dto.CartUpdateRequest;
import com.project.lbms.dto.ProjectResponseEntity;
import com.project.lbms.dto.UserCartBook;
import com.project.lbms.service.CartService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/cart")
@Tag(name = "Cart Controller", description = "Endpoints related to user's cart")
public class CartController{

    private CartService cartService;
    private static final String CART_CONTROLLER_STR = "CartController";

    @Operation(
        summary = "Get the User's Cart",
        description = "",
        responses = {
            @ApiResponse(
                responseCode = "200",
                content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = UserCartBook.class))))})
    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<Object> getUserCart(
        @PathVariable String userId
    ) throws Exception{
        log.info("{} getUserCart {}", CART_CONTROLLER_STR, userId);
        return cartService.getUserCart(userId);
    }


    @Operation(
        summary = "Get the Cart using the cart Id",
        description = "",
        responses = {
            @ApiResponse(
                responseCode = "201",
                content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = UserCartBook.class))))})
    @GetMapping(path = "/{cartId}")
    public ResponseEntity<Object> getCart(
        @PathVariable String cartId
        // Decrypt token to validate the user
    ) throws Exception{
        log.info("{} getCart {}", CART_CONTROLLER_STR, cartId);
        return cartService.getUserCart(cartId);
    }


    @Operation(
        summary = "Updates / Adds the given Cart",
        description = "",
        responses = {
            @ApiResponse(
                responseCode = "201",
                content = @Content(
                    schema = @Schema(implementation = ProjectResponseEntity.class)))})
    @PutMapping(path = "/{userId}")
    public ResponseEntity<Object> updateUserCart(
        @PathVariable String userId,
        @Valid @RequestBody CartUpdateRequest cartDto
    ) throws Exception{
        log.info("{} updateUserCart {}", CART_CONTROLLER_STR, userId);
        return cartService.updateUserCart(userId, cartDto);
    }
}
