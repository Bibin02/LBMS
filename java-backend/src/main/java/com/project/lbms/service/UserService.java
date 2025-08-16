package com.project.lbms.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.project.lbms.constants.LbmsConstants;
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

    public List<UsersVO> findAllUsers(int pageNumber) {
        log.info("{} findAllUsers ", USER_SERVICE_STR);
        return usersRepository.findUsers(PageRequest.of(pageNumber, LbmsConstants.PAGE_SIZE)).getContent();
    }

    public UsersVO findUserById(String id) throws LbmsException{
        log.info("{} findUserById {}", USER_SERVICE_STR, id);
        UsersVO user;
        if((user = usersRepository.findUserById(id).orElse(null)) == null){
            throw new LbmsException(HttpStatus.NOT_FOUND, "User Not Found for the given id " + id);
        }
        return user;
    }
}
