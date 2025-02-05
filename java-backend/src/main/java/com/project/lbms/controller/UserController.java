package com.project.lbms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.lbms.model.Users;
import com.project.lbms.repository.UsersRepository;

@Controller
public class UserController {

    @Autowired
    UsersRepository urepo;

    @RequestMapping(
        method = RequestMethod.GET, 
        path = "/api/v1/user/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object getUser(@PathVariable String id){
        return urepo.findById(Integer.parseInt(id));
    }

    @RequestMapping(
        method = RequestMethod.GET, 
        path = "/api/v1/users", 
        produces = MediaType.APPLICATION_JSON_VALUE)

    @ResponseBody()
    public List<Users> getUsers(){
        return urepo.findAll();
    }
}
