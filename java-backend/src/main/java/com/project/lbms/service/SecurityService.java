package com.project.lbms.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.lbms.dto.ProjectResponseEntity;
import com.project.lbms.repository.UsersRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SecurityService {

    private UsersRepository usersRepository;

    public SecurityService(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    public ResponseEntity<Object> login(String userId, String password) {
        if (usersRepository.findByUserIdAndPass(userId, password).orElse(null) == null) {
            log.info("login failed for user id {}", userId);
            return ResponseEntity.badRequest().body(
                ProjectResponseEntity.getProjectResponseEntity("Invalid User", HttpStatus.BAD_REQUEST.value())
            );
        }
        log.info("login Success for user id {}", userId);
        return ResponseEntity.accepted().body(
            ProjectResponseEntity.getProjectResponseEntity("token", HttpStatus.ACCEPTED.value())
        );
    }

}
