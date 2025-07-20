package com.project.lbms.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import com.project.lbms.exception.LbmsException;
import com.project.lbms.model.Book;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PagedSellerBookSummary {
    private List<SellerBookSummary> data;
    private int page;
    private int booksPerPage;
    private int totalPages;
    public static PagedSellerBookSummary build(Page<Book> booksPaginated) throws LbmsException{
        return new PagedSellerBookSummary(
            SellerBookSummary.build(booksPaginated.getContent()), 
            booksPaginated.getNumber(), 
            booksPaginated.getNumberOfElements(), 
            booksPaginated.getTotalPages()
        );
    }
}
