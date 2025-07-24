package com.project.lbms.dto;

import java.util.ArrayList;
import java.util.List;

import com.project.lbms.model.Book;
import com.project.lbms.model.Cart;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCartBook {
    private String bookUid;
    private String bookName;
    private String imageSource;
    private int quantity;
    private double cost;
    private boolean isLend;
    private long lendDuration;

    public static List<UserCartBook> build(List<Cart> carts) {
        List<UserCartBook> userCarts = new ArrayList<>();
        if (carts.isEmpty()){
            return userCarts;
        }
        carts
            .get(0)
            .getCartBooks()
            .stream()
            .forEach(cartBook->{
                Book book = cartBook.getCartBookIdObject();
                userCarts.add(
                    new UserCartBook(
                        book.getBookUid(), 
                        book.getBookName(), 
                        book.getImageSource(), 
                        cartBook.getBookCount(),
                        book.getCost(),
                        book.getLendableBook() != null, 
                        book.getLendableBook() != null ? book.getLendableBook().getDuration() : 0L)
                    );
            });
        return userCarts;
    }
}
