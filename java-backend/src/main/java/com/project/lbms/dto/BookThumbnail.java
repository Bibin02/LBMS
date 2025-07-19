package com.project.lbms.dto;

import com.project.lbms.model.Book;
import com.project.lbms.model.Review;

import lombok.Data;

@Data
public class BookThumbnail {
    private BookThumbnail(Book book){
        this.bookUid = book.getBookUid();
        this.bookName = book.getBookName();
        this.imageSource = book.getImageSource();
        this.author = book.getAuthors();
        this.cost = book.getCost();
        this.discount = book.getDiscount();
        this.rating = book.getReviews()
                        .stream()
                        .mapToDouble(Review::getRating)
                        .average()
                        .orElse(0.0);
    }
    private String bookUid;
    private String bookName;
    private String author;
    private Double cost;
    private Integer discount;
    private double rating;
    private String imageSource;
    private Boolean doPreview = Boolean.FALSE;

    public static BookThumbnail build(Book book){
        return new BookThumbnail(book);
    }
}
