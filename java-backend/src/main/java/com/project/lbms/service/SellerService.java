package com.project.lbms.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.project.lbms.constants.LbmsConstants;
import com.project.lbms.dto.PaginatedResponse;
import com.project.lbms.dto.SellerBookSummary;
import com.project.lbms.dto.SellerBookVO;
import com.project.lbms.exception.LbmsException;
import com.project.lbms.repository.BookRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SellerService {
    private BookRepository bookRepository;
    private static final String SELLER_SERVICE_STR = "SellerService";

    public SellerService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public SellerBookVO getSellerBook(String sellerUid, String bookUid) throws LbmsException{
        log.info("{} getSellerBook {}", SELLER_SERVICE_STR, sellerUid);
        return bookRepository.findByBookSellerSellerIdAndBookUid(sellerUid, bookUid).orElseThrow(
                ()-> new LbmsException(HttpStatus.NOT_FOUND, LbmsConstants.BOOK_NOT_FOUND + bookUid)
            );
    }

    public PaginatedResponse getSellerBooks(String sellerUid, int pageNumber) throws LbmsException{
        log.info("{} getSellerBooks {}", SELLER_SERVICE_STR, sellerUid);
        var pagedBooks = bookRepository.findByBookSellerSellerId(sellerUid, PageRequest.of(pageNumber, LbmsConstants.PAGE_SIZE));
        return PaginatedResponse.build(pagedBooks, rawData -> SellerBookSummary.build(pagedBooks.getContent()));
    }
}
