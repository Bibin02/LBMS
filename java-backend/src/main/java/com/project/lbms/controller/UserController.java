package com.project.lbms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.lbms.model.Users;
import com.project.lbms.service.UserService;

@RestController
@RequestMapping
(
    path = "/api/v1"
)
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(
        method = RequestMethod.GET, 
        path = "/user/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object getUser(@PathVariable String id){
        return userService.findUserById(id);
    }

    @RequestMapping(
        method = RequestMethod.GET, 
        path = "/users", 
        produces = MediaType.APPLICATION_JSON_VALUE)

    @ResponseBody()
    public List<Users> getUsers(){
        return userService.findAllUsers();
    }
}
