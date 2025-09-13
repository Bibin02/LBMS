package com.project.lbms.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.project.lbms.constants.ApplicationRole;
import com.project.lbms.constants.LbmsConstants;
import com.project.lbms.dto.PaginatedResponse;
import com.project.lbms.dto.RegisterUser;
import com.project.lbms.dto.UsersVO;
import com.project.lbms.exception.LbmsException;
import com.project.lbms.model.Users;
import com.project.lbms.repository.UsersRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
    
    private UsersRepository usersRepository;
    private static final String USER_SERVICE_STR = "UserService";

    public UserService(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    public Users findMasterUserById(String id) throws LbmsException{
        log.info("{} findMasterUserById {}", USER_SERVICE_STR, id);
        Users user;
        if((user = usersRepository.findById(id).orElse(null)) == null){
            throw new LbmsException(HttpStatus.NOT_FOUND, "User Not Found for the given id " + id);
        }
        return user;
    }

    public PaginatedResponse findAllUsers(int pageNumber){
        log.info("{} findAllUsers ", USER_SERVICE_STR);
        return PaginatedResponse.build(
            usersRepository.findUsers(PageRequest.of(pageNumber, LbmsConstants.PAGE_SIZE)), pageNumber);
    }

    public UsersVO findUserById(String id) throws LbmsException{
        log.info("{} findUserById {}", USER_SERVICE_STR, id);
        UsersVO user;
        if((user = usersRepository.findUserById(id).orElse(null)) == null){
            throw new LbmsException(HttpStatus.NOT_FOUND, "User Not Found for the given id " + id);
        }
        return user;
    }

    public PaginatedResponse getUserLendBooks(int pageNumber, String userId){
        log.info("{} getUserLendBooks {}", USER_SERVICE_STR, userId);
        return PaginatedResponse.build(
            usersRepository.findUserLendBooks(
                userId, PageRequest.of(pageNumber, LbmsConstants.PAGE_SIZE)), 
                pageNumber);
    }

    public String registerUser(RegisterUser registerUser) throws LbmsException{
        log.info("{} registerUser {}", USER_SERVICE_STR, registerUser.getUserId());
        if(usersRepository.findById(registerUser.getUserId()).orElse(null) != null){
            throw new LbmsException(HttpStatus.CONFLICT, 
            "User already present for the given id " + registerUser.getUserId());
        }
        Users user = new Users();
        user.setUserId(registerUser.getUserId());
        user.setUserName(registerUser.getUserId());
        user.setPass(registerUser.getPassword());
        user.setUserAddress(registerUser.getAddress());
        user.setUserDescription(registerUser.getAbout());
        user.setRole(ApplicationRole.valueOf(registerUser.getRole()));
        usersRepository.save(user);
        return registerUser.getUserId() + "," + "User Registered Successfully";
    }
}
