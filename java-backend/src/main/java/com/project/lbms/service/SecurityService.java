package com.project.lbms.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.lbms.dto.ProjectResponseEntity;
import com.project.lbms.model.Users;
import com.project.lbms.repository.UsersRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class SecurityService implements UserDetailsService{

    private UsersRepository usersRepository;
    private PasswordEncoder encoder;

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

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Users user = null;
        user = usersRepository.findById(userId).orElseThrow(() -> 
            new UsernameNotFoundException(String.format("User %s not found in our records", userId))
        );
        return User
            .withUsername(userId)
            .password(user.getPass())
            .authorities(user.getRole().toString())
            .passwordEncoder(password -> encoder.encode(password))
            .build();
    }

}
