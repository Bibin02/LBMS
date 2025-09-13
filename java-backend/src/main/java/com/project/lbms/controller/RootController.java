package com.project.lbms.controller;

import java.time.Instant;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.lbms.dto.LoginUser;
import com.project.lbms.service.SecurityService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/")
public class RootController {

    private SecurityService securityService;

    public RootController(SecurityService securityService){
        this.securityService = securityService;
    }

    @RequestMapping(path = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<Object> getRoot(HttpServletRequest request){
        log.info("Home Page visited by {}", request.getSession().getId());
        return ResponseEntity.ok(
            Map.of(
            "api", "Lbms REST API",
            "version", "v1.0.0",
            "description", "Welcome to the REST API. Explore the available endpoints using the links below.",
            "status", "UP",
            "timestamp", Instant.now().toString(),
            "links", Map.of(
                "swagger_ui", "/scalar",
                "openapi_spec", "/v3/api-docs",
                "health", "/actuator/health"
            )
        ));
    }

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(
        @Valid @RequestBody LoginUser loginUser
    ){
        log.info("Login Attempt by {}", loginUser.getUserId());
        return securityService.login(loginUser.getUserId(), loginUser.getPassword());
    }
}