package com.project.lbms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.lbms.model.Users;
import com.project.lbms.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping
(
    path = "/api/v1"
)
public class UserController {

    @Autowired
    private UserService userService;

    private final String USER_CONTROLLER_STR = "UserController ";

    @GetMapping(
        path = "/user/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getUser(@PathVariable String id){
        log.info(USER_CONTROLLER_STR + "getUser " + id);
        return userService.findUserById(id);
    }

    @GetMapping(
        path = "/users", 
        produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Users> getUsers(){
        log.info(USER_CONTROLLER_STR + "getUsers ");
        return userService.findAllUsers();
    }
}
