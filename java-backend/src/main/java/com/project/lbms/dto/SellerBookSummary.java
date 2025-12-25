package com.project.lbms.dto;

import java.util.ArrayList;
import java.util.List;

import com.project.lbms.model.Book;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SellerBookSummary {
    private String bookUid;
    private String bookName;
    private Double prize;
    private Integer stock;

    public static List<SellerBookSummary> build(List<Book> books){
        List<SellerBookSummary> sellerBookSummaries = new ArrayList<>();
        books.stream().forEach(book->{
            SellerBookSummary sellerBookSummary = new SellerBookSummary(
                book.getBookUid(), book.getBookName(), book.getCost(), book.getStock());
            sellerBookSummaries.add(sellerBookSummary);
        });
        return sellerBookSummaries;
    }    
}
