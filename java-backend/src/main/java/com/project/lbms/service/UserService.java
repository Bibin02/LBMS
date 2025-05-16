package com.project.lbms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.lbms.model.Users;
import com.project.lbms.repository.UsersRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
    
    @Autowired
    private UsersRepository usersRepository;

    private final String USER_SERVICE_STR = "UserService ";

    public Object findUserById(String id){
        log.info(USER_SERVICE_STR + "findUserById " + id);
        return usersRepository.findById(id);

    }

    public List<Users> findAllUsers() {
        log.info(USER_SERVICE_STR + "findAllUsers ");
        return usersRepository.findAll();
    }
}
