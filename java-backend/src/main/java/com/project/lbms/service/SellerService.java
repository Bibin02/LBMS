package com.project.lbms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.lbms.constants.CartType;
import com.project.lbms.constants.LbmsConstants;
import com.project.lbms.dto.PaginatedResponse;
import com.project.lbms.dto.SellerBookSummary;
import com.project.lbms.dto.SellerBookVO;
import com.project.lbms.dto.SellerSummary;
import com.project.lbms.dto.StockData;
import com.project.lbms.exception.LbmsException;
import com.project.lbms.model.Cart;
import com.project.lbms.model.CartBook;
import com.project.lbms.model.CartBookId;
import com.project.lbms.model.Seller;
import com.project.lbms.repository.BookRepository;
import com.project.lbms.repository.CartBookRepository;
import com.project.lbms.repository.CartRepository;
import com.project.lbms.repository.SellerRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class SellerService {

    private BookRepository bookRepository;
    private CartRepository cartRepository;
    private CartBookRepository cartBookRepository;
    private SellerRepository sellerRepository;
    private static final String SELLER_SERVICE_STR = "SellerService";

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

    @Transactional
    public void placeSellerOrders(String cartId) {
        Map<Seller, Cart> sellerMap = new HashMap<>();
        for (var userCartbook : cartBookRepository.findByIdBookCartUid(cartId)) {
            var book = userCartbook.getCartBookIdObject();
            var bookSeller = book.getBookSeller();
            if (sellerMap.containsKey(bookSeller)) {
                var cart = sellerMap.get(bookSeller);
                var sellerCartBook = new CartBook();
                var sellerCartBookId = new CartBookId();
                sellerCartBook.setBookCartIdObject(cart);
                sellerCartBook.setBookCount(userCartbook.getBookCount());
                sellerCartBook.setCartBookIdObject(book);
                sellerCartBook.setLended(userCartbook.isLended());
                sellerCartBookId.setBookCartUid(cart.getCartId());
                sellerCartBookId.setCartBookUid(book.getBookUid());
                sellerCartBook.setCartBookId(sellerCartBookId);
                cartBookRepository.save(sellerCartBook);
            }
            else{
                var cart = new Cart();
                cart.setCartId(UUID.randomUUID().toString());
                cart.setCartType(CartType.ORDER_CART);
                cart.setCartUser(bookSeller.getSellerInfo());
                cartRepository.save(cart);
                
                var sellerCartBook = new CartBook();
                var sellerCartBookId = new CartBookId();
                sellerCartBook.setBookCartIdObject(cart);
                sellerCartBook.setBookCount(userCartbook.getBookCount());
                sellerCartBook.setCartBookIdObject(book);
                sellerCartBook.setLended(userCartbook.isLended());
                sellerCartBookId.setBookCartUid(cart.getCartId());
                sellerCartBookId.setCartBookUid(book.getBookUid());
                sellerCartBook.setCartBookId(sellerCartBookId);
                cartBookRepository.save(sellerCartBook);
                
                sellerMap.put(bookSeller, cart);
            }
        }
    }
}
