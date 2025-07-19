package com.project.lbms.dto;

import java.util.List;
import java.util.Map;

import com.project.lbms.model.Book;
import com.project.lbms.model.Review;

import lombok.Data;

@Data
public class BookVO {
    private BookVO(Book book){
        this.bookUid = book.getBookUid();
        this.bookName = book.getBookName();
        this.imageSource = book.getImageSource();
        this.authorName = book.getAuthors();
        this.publicationName = book.getPublication();
        this.sellerName = book.getBookSeller().getSellerName();
        this.bookSellStatus = book.getLendableBook() != null;
        this.bookLendDuration = book.getLendableBook() != null ? book.getLendableBook().getDuration() : null;
        setKeywords(book.getKeywords()); 
        this.cost = book.getCost();
        this.discount = book.getDiscount();
        this.rating = book.getReviews()
                        .stream()
                        .mapToDouble(Review::getRating)
                        .average()
                        .orElse(0.0);
        this.stock = book.getStock();
        this.bookDescription = book.getBookDescription();
    }
    private String bookUid;
    private String bookName;
    private String imageSource;
    private String authorName;
    private Boolean bookSellStatus;
    private Long bookLendDuration;
    private String sellerName;
    private String publicationName;
    private List<String> keywords;
    private Double cost;
    private Integer discount;
    private double rating;
    private Integer stock;
    private Map<String, Object> bookDescription;

    private void setKeywords(String keywords){
        this.keywords = List.of(keywords.split(","));
    }

    public static BookVO build(Book book){
        return book != null ? new BookVO(book) : null;
    }
}
