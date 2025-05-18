package com.project.lbms.service;

import java.util.List;
import java.util.Optional;

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

    private static final String USER_SERVICE_STR = "UserService";

    public Optional<Users> findUserById(String id){
        log.info("{} findUserById {}", USER_SERVICE_STR, id);
        return usersRepository.findById(id);

    }

    public List<Users> findAllUsers() {
        log.info("{} findAllUsers ", USER_SERVICE_STR);
        return usersRepository.findAll();
    }
}
