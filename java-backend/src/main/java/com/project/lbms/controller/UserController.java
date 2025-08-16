package com.project.lbms.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.lbms.exception.LbmsException;
import com.project.lbms.service.UserService;
import com.project.lbms.util.LbmsResponseEntityBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping
(
    path = "/api/v1"
)
public class UserController extends LbmsResponseEntityBuilder{

    private UserService userService;
    private static final String USER_CONTROLLER_STR = "UserController";

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(path = "/masterUser/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getMasterUser(@PathVariable String id) throws LbmsException{
        log.info( "{} getMasterUser {}",USER_CONTROLLER_STR, id);
        return getResponseEntityOk(userService.findMasterUserById(id));
    }

    @GetMapping(path = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUser(@PathVariable String id) throws LbmsException{
        log.info( "{} getUser {}",USER_CONTROLLER_STR, id);
        return getResponseEntityOk(userService.findUserById(id));
    }

    @GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUsers(
        @RequestParam(required = false, defaultValue = "0") int pageNumber
    ){
        log.info( "{} getUsers",USER_CONTROLLER_STR);
        return getResponseEntityOk(userService.findAllUsers(pageNumber));
    }
}
