package com.project.lbms.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.project.lbms.model.Book;
import com.project.lbms.model.CartBook;

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

    public static List<OrderBook> build(Set<CartBook> books){
        List<OrderBook> orderBooks = new ArrayList<>();
        for (CartBook cartbook : books){
            Book book = cartbook.getCartBookIdObject();
            orderBooks.add(new OrderBook(
                book.getImageSource(), 
                book.getBookName(), 
                cartbook.getBookCount(), 
                book.getLendableBook() != null)
                );
            }
        return orderBooks;
    }
}
