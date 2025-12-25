package com.project.lbms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.lbms.constants.CartType;
import com.project.lbms.constants.LbmsConstants;
import com.project.lbms.dto.CartUpdateRequest;
import com.project.lbms.dto.ProjectResponseEntity;
import com.project.lbms.dto.UserCartBook;
import com.project.lbms.exception.LbmsException;
import com.project.lbms.model.Book;
import com.project.lbms.model.Cart;
import com.project.lbms.model.CartBook;
import com.project.lbms.model.CartBookId;
import com.project.lbms.repository.BookRepository;
import com.project.lbms.repository.CartBookRepository;
import com.project.lbms.repository.CartRepository;
import com.project.lbms.repository.UsersRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class CartService {
    private CartRepository cartRepository;
    private CartBookRepository cartBookRepository;
    private UsersRepository usersRepository;
    private BookRepository bookRepository;
    private static final String CART_SERVICE_STR = "CartService";
    private static final String CART_URI = "/cart/";

    @Transactional
    public ResponseEntity<Object> getUserCart(String userId) throws LbmsException{
        log.info("{} getUserCart {}",CART_SERVICE_STR, userId);
        List<UserCartBook> userCartBooks = new ArrayList<>();
        List<Cart> carts = cartRepository.findByCartUserAndCartType(
            usersRepository.findById(userId).orElseThrow(
                ()-> new LbmsException(HttpStatus.NOT_FOUND, LbmsConstants.USER_NOT_FOUND + userId)), 
            CartType.CART, 
            PageRequest.ofSize(1))
            .getContent();
        if (carts.isEmpty()){
            return ResponseEntity.ok().body(userCartBooks);
        }
        for (CartBook cartBook : cartBookRepository.findByIdBookCartUid(carts.get(0).getCartId())){
            Book book = cartBook.getCartBookIdObject();
            userCartBooks.add(UserCartBook.build(book, cartBook));
        }
        return ResponseEntity.ok()
        .location(LbmsConstants.createUri(CART_URI + carts.get(0).getCartId()))
        .body(userCartBooks);
    }

    @Transactional
    public ResponseEntity<Object> getCart(String cartId) throws LbmsException{
        log.info("{} getUserCart {}",CART_SERVICE_STR, cartId);
        List<UserCartBook> userCartBooks = new ArrayList<>();
        var cart = cartRepository.findById(cartId).orElseThrow(()->
            new LbmsException(HttpStatus.NOT_FOUND, LbmsConstants.CART_NOT_FOUND));

        for (CartBook cartBook : cart.getCartBooks()){
            Book book = cartBook.getCartBookIdObject();
            userCartBooks.add(UserCartBook.build(book, cartBook));
        }
        return ResponseEntity.ok()
        .location(LbmsConstants.createUri(CART_URI + cartId))
        .body(userCartBooks);
    }

    @Transactional
    public ResponseEntity<Object> updateUserCart(String userId, CartUpdateRequest cartDto) 
    throws LbmsException{
        log.info("{} updateUserCart {}", CART_SERVICE_STR, userId);
        var user = usersRepository.findById(userId).orElseThrow(
                ()-> new LbmsException(HttpStatus.NOT_FOUND, LbmsConstants.USER_NOT_FOUND + userId));
        var book = bookRepository.findById(cartDto.getBookUid()).orElseThrow(
                ()-> new LbmsException(HttpStatus.NOT_FOUND, LbmsConstants.BOOK_NOT_FOUND + cartDto.getBookUid()));
        var carts = cartRepository
            .findByCartUserAndCartType(user, CartType.CART, PageRequest.ofSize(1))
            .getContent();

        if (carts.isEmpty()) {
            log.info("{} Cart not found, creating new cart for {}", CART_SERVICE_STR, userId);
            var cart = new Cart();
            String cartId = UUID.randomUUID().toString();
            cart.setCartId(cartId);
            cart.setCartType(CartType.CART);
            cart.setCartUser(user);
            cartRepository.save(cart);
            generateCartBook(book, cart, cartDto);
            return ResponseEntity.created(LbmsConstants.createUri(CART_URI + cartId))
            .body(ProjectResponseEntity.getProjectResponseEntity(
            "Cart Created Successfully", HttpStatus.CREATED.value()));
        }
        var cart = carts.get(0);
        log.info("{} Cart found for {} id : {}", CART_SERVICE_STR, userId, cart.getCartId());
        updateCartBook(book, cart, cartDto);

        return ResponseEntity.accepted()
            .location(LbmsConstants.createUri(CART_URI + cart.getCartId()))
            .body(ProjectResponseEntity.getProjectResponseEntity(
            "Cart Updated Successfully", HttpStatus.ACCEPTED.value()));
    }

    private void updateCartBook(Book book, Cart cart, CartUpdateRequest cartDto) throws LbmsException{
        var cartBookId = new CartBookId();
        cartBookId.setBookCartUid(cart.getCartId());
        cartBookId.setCartBookUid(cartDto.getBookUid());
        CartBook cartBook = null;
        if ((cartBook = cartBookRepository.findById(cartBookId).orElse(null)) == null){
            cartBook = new CartBook();
            cartBook.setCartBookId(cartBookId);
            cartBook.setBookCartIdObject(cart);
            cartBook.setCartBookIdObject(book);
            cartBook.setBookCount(validateStocks(cartDto.getQuantity(), book));
            cartBook.setLended(validateLended(cartDto.isLend(), book));
            cartBookRepository.save(cartBook);
        }
        cartBook.setBookCount(validateStocks(cartDto.getQuantity(), book));
        cartBook.setLended(validateLended(cartDto.isLend(), book));
    }

    private void generateCartBook(Book book, Cart cart, CartUpdateRequest cartDto) throws LbmsException{
        var cartBook = new CartBook();
        var cartBookId = new CartBookId();
        cartBookId.setBookCartUid(cart.getCartId());
        cartBookId.setCartBookUid(cartDto.getBookUid());
        cartBook.setCartBookId(cartBookId);
        cartBook.setBookCount(validateStocks(cartDto.getQuantity(), book));
        cartBook.setLended(validateLended(cartDto.isLend(), book));
        cartBook.setBookCartIdObject(cart);
        cartBook.setCartBookIdObject(book);
        cartBookRepository.save(cartBook);
    }

    private int validateStocks(int requestStock, Book book) throws LbmsException {
        if (book.getStock() < requestStock) {
            throw new LbmsException(HttpStatus.BAD_REQUEST, "Requested Books are more than existing stocks");
        }
        return requestStock;
    }

    private boolean validateLended(boolean requestLend, Book book) throws LbmsException{
        if (book.getLendableBook() == null && requestLend) {
            throw new LbmsException(HttpStatus.BAD_REQUEST, "Requested Book not available for Lend");
        }
        return requestLend;
    }
}
