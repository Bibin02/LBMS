package com.project.lbms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.lbms.model.Users;
import com.project.lbms.repository.UsersRepository;

@Service
public class UserService {
    
    public final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    public Object findUserById(String Id){
        return usersRepository.findById(Id);

    }

    public List<Users> findAllUsers() {
        return usersRepository.findAll();
    }
}
