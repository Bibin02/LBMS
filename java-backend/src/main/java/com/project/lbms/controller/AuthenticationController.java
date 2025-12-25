package com.project.lbms.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.lbms.constants.LbmsConstants;
import com.project.lbms.dto.LoginUser;
import com.project.lbms.dto.ProjectResponseEntity;
import com.project.lbms.dto.RegisterUser;
import com.project.lbms.exception.LbmsException;
import com.project.lbms.service.UserService;
import com.project.lbms.util.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/auth")
@Tag(name = "Authentication Controller", description = "Used to add and authorize users")
public class AuthenticationController{

    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;
    private UserService userService;
    private static final String AUTH_CONTROLLER_STR = "AuthenticationController";

    @Operation(
        summary = "Login into the application",
        responses = {
            @ApiResponse(
                responseCode = "202",
                description = "Successful login",
                content = @Content(
                    schema = @Schema(implementation = ProjectResponseEntity.class))),
            @ApiResponse(
                responseCode = "400",
                description = "Bad Credentials",
                content = @Content(
                    schema = @Schema(implementation = ProjectResponseEntity.class)))
                })
    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(
        @Valid @RequestBody LoginUser loginUser
    ) throws Exception{
        log.info("Login Attempt by {}", loginUser.getUserId());
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUserId(),
                        loginUser.getPassword()
                )
        );
        return ResponseEntity.ok(Map.of("token", jwtUtil.generateToken((User) auth.getPrincipal())));
    }

    @Operation(
        summary = "Register new user into the application",
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = "Successful user creation",
                content = @Content(
                    schema = @Schema(implementation = ProjectResponseEntity.class)))
                })
    @PostMapping(path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registerUser(
        @Valid @RequestBody RegisterUser registerUser
    ) throws LbmsException{
        log.info("{} registerUser {}", AUTH_CONTROLLER_STR, registerUser.getUserId());
        String[] response = userService.registerUser(registerUser).split(",");
        return ResponseEntity
                .created(LbmsConstants.createUri(String.format("/user/%s",response[0])))
                .body(Map.of("message", response[1], 
                "responseCode", HttpStatus.CREATED.value()));
    }

}
