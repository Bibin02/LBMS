package com.project.lbms.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.project.lbms.constants.LbmsConstants;
import com.project.lbms.dto.PaginatedResponse;
import com.project.lbms.dto.SellerBookSummary;
import com.project.lbms.dto.SellerBookVO;
import com.project.lbms.dto.SellerSummary;
import com.project.lbms.dto.StockData;
import com.project.lbms.exception.LbmsException;
import com.project.lbms.repository.BookRepository;
import com.project.lbms.repository.SellerRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SellerService {
    private BookRepository bookRepository;
    private SellerRepository sellerRepository;
    private static final String SELLER_SERVICE_STR = "SellerService";

    public SellerService(SellerRepository sellerRepository, BookRepository bookRepository){
        this.bookRepository = bookRepository;
        this.sellerRepository = sellerRepository;
    }

    public SellerBookVO getSellerBook(String sellerUid, String bookUid) throws LbmsException{
        log.info("{} getSellerBook {}", SELLER_SERVICE_STR, sellerUid);
        var sellerBookVO = bookRepository.findByBookSellerSellerInfoUserIdAndBookUid(sellerUid, bookUid).orElseThrow(
                ()-> new LbmsException(HttpStatus.NOT_FOUND, LbmsConstants.BOOK_NOT_FOUND + bookUid)
            );
        var lendableBook = sellerBookVO.getLendableBook();
        if (lendableBook != null) {
            sellerBookVO.setIsLended(true);
            sellerBookVO.setLendDuration(lendableBook.getDuration());
            sellerBookVO.setLendCost(lendableBook.getLendCost());
        }
        return sellerBookVO;
    }

    public PaginatedResponse getSellerBooks(String sellerUid, int pageNumber) throws LbmsException{
        log.info("{} getSellerBooks {}", SELLER_SERVICE_STR, sellerUid);
        var pagedBooks = bookRepository.findByBookSellerSellerId(sellerUid, PageRequest.of(pageNumber, LbmsConstants.PAGE_SIZE));
        return PaginatedResponse.build(pagedBooks, pageNumber, rawData -> SellerBookSummary.build(pagedBooks.getContent()));
    }

    public SellerSummary getSellerDashboard(String sellerUid) throws LbmsException{
        var seller = sellerRepository.findById(sellerUid).orElseThrow(
            ()-> new LbmsException(HttpStatus.NOT_FOUND, LbmsConstants.SELLER_NOT_FOUND + sellerUid)
        );
        int totalBooks = bookRepository.sellerTotalBooks(sellerUid);
        int soldBooks = seller.getBookSoldCount();
        int onDelivery = totalBooks - soldBooks;
        var salesDataList = sellerRepository.getWeeklySales(sellerUid);
        var stockDataList = List.of(
            new StockData("totalBooks", totalBooks),
            new StockData("soldBooks", soldBooks),
            new StockData("onDelivery", onDelivery));
        return new SellerSummary(
            sellerUid, 
            seller.getSellerInfo().getUserName(), 
            totalBooks,
            soldBooks,
            onDelivery, 
            seller.getEarnings(), 
            salesDataList,
            stockDataList);
    }
}
