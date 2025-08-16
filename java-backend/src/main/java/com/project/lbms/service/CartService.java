package com.project.lbms.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.lbms.constants.LbmsConstants;
import com.project.lbms.dto.UserCartBook;
import com.project.lbms.exception.LbmsException;
import com.project.lbms.model.Book;
import com.project.lbms.model.Cart;
import com.project.lbms.model.CartBook;
import com.project.lbms.repository.CartBookRepository;
import com.project.lbms.repository.CartRepository;
import com.project.lbms.repository.UsersRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CartService {
    private CartRepository cartRepository;
    private UsersRepository usersRepository;
    private CartBookRepository cartBookRepository;
    private static final String CART_SERVICE_STR = "CartService";

    public CartService(CartRepository cartRepository, UsersRepository usersRepository, CartBookRepository cartBookRepository){
        this.cartRepository = cartRepository;
        this.usersRepository = usersRepository;
        this.cartBookRepository = cartBookRepository;
    }

    @Transactional
    public List<UserCartBook> getUserCart(String userId) throws LbmsException{
        log.info("{} getUserCart {}",CART_SERVICE_STR, userId);
        List<UserCartBook> userCartBooks = new ArrayList<>();
        List<Cart> carts = cartRepository.findByCartUserAndIsOrderedFalse(
            usersRepository.findById(userId).orElseThrow(
                ()-> new LbmsException(HttpStatus.NOT_FOUND, LbmsConstants.USER_NOT_FOUND + userId)
            ));
        if (carts.isEmpty()){throw new LbmsException(HttpStatus.NOT_FOUND, LbmsConstants.CART_NOT_FOUND);}
        for (CartBook cartBook : cartBookRepository.findByIdBookCartUid(carts.get(0).getCartId())){
            Book book = cartBook.getCartBookIdObject();
            userCartBooks.add(UserCartBook.build(book, cartBook));
        }
        return userCartBooks;
    }
}
