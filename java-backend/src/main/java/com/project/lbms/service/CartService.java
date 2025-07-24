package com.project.lbms.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.lbms.constants.LbmsConstants;
import com.project.lbms.dto.UserCartBook;
import com.project.lbms.exception.LbmsException;
import com.project.lbms.repository.CartRepository;
import com.project.lbms.repository.UsersRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CartService {
    private CartRepository cartRepository;
    private UsersRepository usersRepository;
    private static final String CART_SERVICE_STR = "CartService";

    public CartService(CartRepository cartRepository, UsersRepository usersRepository){
        this.cartRepository = cartRepository;
        this.usersRepository = usersRepository;
    }

    @Transactional
    public List<UserCartBook> getUserCart(String userId) throws LbmsException{
        log.info("{} getUserCart {}",CART_SERVICE_STR, userId);
        return UserCartBook.build(
            cartRepository.findByCartUserAndIsOrderedFalse(
                usersRepository.findById(userId).orElseThrow(
                    ()-> new LbmsException(HttpStatus.NOT_FOUND, LbmsConstants.USER_NOT_FOUND + userId)
                )
            )
        );
    }
}
