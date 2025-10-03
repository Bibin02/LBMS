package com.project.lbms.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.lbms.constants.LbmsConstants;
import com.project.lbms.dto.ProjectResponseEntity;
import com.project.lbms.dto.RegisterUser;
import com.project.lbms.dto.UpdateUserDto;
import com.project.lbms.exception.LbmsException;
import com.project.lbms.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
@Tag(name = "User Controller", description = "Endpoints related to Users")
public class UserController{

    private UserService userService;
    private static final String USER_CONTROLLER_STR = "UserController";

    @GetMapping(path = "/masterUser/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getMasterUser(@PathVariable String id) throws LbmsException{
        log.info( "{} getMasterUser {}",USER_CONTROLLER_STR, id);
        return ResponseEntity.ok(userService.findMasterUserById(id));
    }

    @GetMapping(path = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUser(@PathVariable String id) throws LbmsException{
        log.info( "{} getUser {}",USER_CONTROLLER_STR, id);
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @GetMapping(path = "/user/{id}/lendbooks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUserLendBooks(
        @PathVariable String id,
        @RequestParam(required = false, defaultValue = "0") int pageNumber
    ){
        log.info( "{} getUserLendBooks {}",USER_CONTROLLER_STR, id);
        return ResponseEntity.ok(userService.getUserLendBooks(pageNumber, id));
    }

    @GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUsers(
        @RequestParam(required = false, defaultValue = "0") int pageNumber
    ){
        log.info( "{} getUsers",USER_CONTROLLER_STR);
        return ResponseEntity.ok(userService.findAllUsers(pageNumber));
    }

    @PostMapping(path = "/auth/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registerUser(
        @Valid @RequestBody RegisterUser registerUser
    ) throws LbmsException, URISyntaxException{
        log.info("{} registerUser {}", USER_CONTROLLER_STR, registerUser.getUserId());
        String[] response = userService.registerUser(registerUser).split(",");
        return ResponseEntity
                .created(new URI("/user/"+response[0]))
                .body(ProjectResponseEntity
                .getProjectResponseEntity(response[1], HttpStatus.CREATED.value()));
    }

    @PutMapping(path = "/user/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateUserDetails(
        @Valid @RequestBody UpdateUserDto updatedUser,
        @PathVariable String id
        // Token Authorization needed for the updation
    ) throws LbmsException{
        log.info("{} updateUserDetails {}", USER_CONTROLLER_STR, id);
        return userService.updateUserDetails(updatedUser, id);
    }

    @PutMapping(path = "/user/{id}/password", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Object> updateUserPassword(
        @Valid @NotNull(message = "oldPassword" + LbmsConstants.FIELD_IS_REQUIRED) 
        @RequestParam String oldPassword,
        @Valid @NotNull(message = "newPassword" + LbmsConstants.FIELD_IS_REQUIRED) 
        @RequestParam String newPassword,
        @PathVariable String id
        // Token Authorization needed for the updation
    ) throws LbmsException{
        log.info("{} updateUserPassword {}", USER_CONTROLLER_STR, id);
        return userService.updateUserPassword(oldPassword, newPassword, id);
    }
}
