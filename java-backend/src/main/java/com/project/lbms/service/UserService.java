package com.project.lbms.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.lbms.constants.ApplicationRole;
import com.project.lbms.constants.LbmsConstants;
import com.project.lbms.dto.PaginatedResponse;
import com.project.lbms.dto.ProjectResponseEntity;
import com.project.lbms.dto.RegisterUser;
import com.project.lbms.dto.UpdateUserDto;
import com.project.lbms.dto.UsersVO;
import com.project.lbms.exception.LbmsException;
import com.project.lbms.model.Seller;
import com.project.lbms.model.Users;
import com.project.lbms.repository.SellerRepository;
import com.project.lbms.repository.UsersRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class UserService {
    
    private UsersRepository usersRepository;
    private SellerRepository sellerRepository;
    private static final String USER_SERVICE_STR = "UserService";

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
        usersRepository.findById(registerUser.getUserId()).orElseThrow(()->
            new LbmsException(HttpStatus.CONFLICT, "User already present for the given id " + registerUser.getUserId())
        );
        Users user = new Users();
        String regUserRole = registerUser.getRole();
        user.setUserId(registerUser.getUserId());user.setUserName(registerUser.getUserId());
        user.setPass(registerUser.getPassword());user.setUserAddress(registerUser.getAddress());
        user.setUserDescription(registerUser.getAbout());user.setRole(ApplicationRole.valueOf(regUserRole));
        user = usersRepository.save(user);
        if (regUserRole.equals(ApplicationRole.SELLER.toString()) || regUserRole.equals(ApplicationRole.ADMIN.toString())) {
            Seller seller = new Seller();
            seller.setSellerInfo(user);
            sellerRepository.save(seller);
        }
        return registerUser.getUserId() + "," + "User " + registerUser.getRole() + " Registered Successfully";
    }

    @Transactional
    public ResponseEntity<Object> updateUserDetails(UpdateUserDto updatedUser, String userUid)
    throws LbmsException{
        Users user = null;
        if ((user = usersRepository.findById(userUid).orElse(null)) == null) {
            throw new LbmsException(HttpStatus.NOT_FOUND, LbmsConstants.USER_NOT_FOUND);
        }
        user.setUserName(updatedUser.getUserName());
        user.setUserAddress(updatedUser.getAddress());
        user.setUserDescription(updatedUser.getAbout());
        return ResponseEntity.ok().body(
            ProjectResponseEntity.getProjectResponseEntity(
                String.format("User %s updated", userUid), HttpStatus.OK.value())
        );
    }

    @Transactional
    public ResponseEntity<Object> updateUserPassword(
            String oldPassword,
            String newPassword, 
            String userId) throws LbmsException{
        Users user = null;
        if ((user = usersRepository.findByUserIdAndPass(userId, oldPassword).orElse(null)) == null){
            throw new LbmsException(HttpStatus.NOT_FOUND, LbmsConstants.USER_NOT_FOUND);
        }
        user.setPass(newPassword);
        return ResponseEntity.ok().body(
            ProjectResponseEntity.getProjectResponseEntity(
            String.format("Password Changed for %s user", userId), 
            HttpStatus.OK.value())
        );
    }
}
