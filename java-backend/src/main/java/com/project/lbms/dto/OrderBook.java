package com.project.lbms.dto;

import java.util.ArrayList;
import java.util.List;

import com.project.lbms.model.Book;
import com.project.lbms.model.Cart;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderBook {
    private String imageSource;
    private String bookName;
    private int quantity;
    private boolean isLend;

    public static List<OrderBook> build(Cart cart){
        List<OrderBook> orderBooks = null;
        if (cart != null) {
            orderBooks = new ArrayList<>();
            for (Book book : cart.getCartBooks()){
                System.out.println("Entered\n\n\n\n\n\n");
                orderBooks.add(new OrderBook(
                    book.getImageSource(), 
                    book.getBookName(), 
                    0, 
                    book.getLendableBook() != null)
                    );
            }
        }
        return orderBooks;
    }
}
