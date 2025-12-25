package com.project.lbms.controller;

import java.time.Instant;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/")
@Tag(name = "Home Page", description = "Home Page and root functionalities")
public class RootController {

    @Operation(
        summary = "Home Page of the Application API",
        responses = {
            @ApiResponse(
                responseCode = "200",
                content = @Content(
                    schema = @Schema(implementation = Map.class)))})
    @GetMapping
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
}