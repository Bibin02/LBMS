package com.project.lbms.dto;

import com.project.lbms.model.Book;
import com.project.lbms.model.CartBook;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCartBook {
    private String bookUid;
    private String bookName;
    private String imageSource;
    private int quantity;
    private double cost;
    private boolean isLend;
    private long lendDuration;

    public static UserCartBook build(Book book, CartBook cartBook) {
        return new UserCartBook(
            book.getBookUid(), 
            book.getBookName(), 
            book.getImageSource(), 
            cartBook.getBookCount(),
            book.getCost(),
            cartBook.isLended(), 
            book.getLendableBook() != null ? book.getLendableBook().getDuration() : 0L
        );
    }
}
