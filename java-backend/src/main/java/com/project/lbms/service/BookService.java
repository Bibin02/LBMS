package com.project.lbms.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.lbms.constants.LbmsConstants;
import com.project.lbms.dto.BookDto;
import com.project.lbms.dto.BookThumbnail;
import com.project.lbms.dto.BookVO;
import com.project.lbms.dto.PaginatedResponse;
import com.project.lbms.dto.ProjectResponseEntity;
import com.project.lbms.exception.LbmsException;
import com.project.lbms.model.Book;
import com.project.lbms.model.LendBook;
import com.project.lbms.model.Seller;
import com.project.lbms.repository.BookRepository;
import com.project.lbms.repository.ReviewRepository;
import com.project.lbms.repository.SellerRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookService {
    
    private BookRepository bookRepository;
    private ReviewRepository reviwRepository;
    private SellerRepository sellerRepository;
    private static final String BOOK_SERVICE_STR = "BookService";

    public BookService(BookRepository bookRepository, SellerRepository sellerRepository, ReviewRepository reviewRepository){
        this.bookRepository = bookRepository;
        this.reviwRepository = reviewRepository;
        this.sellerRepository = sellerRepository;
    }

    public Book findMasterBookById(String id) throws LbmsException{
        log.info("{} findMasterBookById {}",BOOK_SERVICE_STR, id);
        return bookRepository.findById(id).orElseThrow(
                ()-> new LbmsException(HttpStatus.NOT_FOUND, LbmsConstants.BOOK_NOT_FOUND + id)
            );
    }

    public PaginatedResponse findAllMasterBooks(int pageNumber) {
        log.info("{} findAllMasterBooks",BOOK_SERVICE_STR);
        return PaginatedResponse.build(
            bookRepository.findAll(PageRequest.of(pageNumber, LbmsConstants.PAGE_SIZE)), pageNumber);
    }

    public BookVO findBookById(String id) throws LbmsException{
        log.info("{} findBookById {}",BOOK_SERVICE_STR, id);
        return BookVO.build(bookRepository.findById(id).orElseThrow(
                ()-> new LbmsException(HttpStatus.NOT_FOUND, LbmsConstants.BOOK_NOT_FOUND + id)
            ));
    }

    /**
     * Recommeded Books
     * 
     * @param pageNumber
     * @return Recommended Books
     */
    public List<BookThumbnail> findAllBooks(int pageNumber) {
        log.info("{} findAllBooks",BOOK_SERVICE_STR);
        List<BookThumbnail> bookThumbnails = new ArrayList<>();
        bookRepository.findAll(PageRequest.of(pageNumber, LbmsConstants.PAGE_SIZE))
            .stream().forEach(book->{
                BookThumbnail bookThumbnail = BookThumbnail.build(book);
                bookThumbnail.setRating(reviwRepository.findBookRating(book.getBookUid()).orElse(5.0));
                bookThumbnails.add(bookThumbnail);
            });
        return bookThumbnails;
    }

    public ResponseEntity<Object> addBook(BookDto bookVO, String userId) throws Exception{
        log.info("{} addBook by {}",BOOK_SERVICE_STR, userId);
        String bookUid = (bookVO.getBookName() + userId).hashCode() + "";
        if (bookRepository.findById(bookUid).orElse(null) != null) {
            return ResponseEntity
            .status(HttpStatus.ALREADY_REPORTED)
            .body(ProjectResponseEntity
        .getProjectResponseEntity("Book Already Present", HttpStatus.ALREADY_REPORTED.value()));
        }
        bookRepository.save(
            mapToBook(new Book(), bookVO, bookUid,
                sellerRepository.findById(userId).orElseThrow(() -> 
                    new LbmsException(HttpStatus.NOT_FOUND, LbmsConstants.SELLER_NOT_FOUND)
                )));
        return ResponseEntity
        .created(new URI("/book/"+bookUid))
        .body(ProjectResponseEntity
        .getProjectResponseEntity("Book Added Successfully", HttpStatus.ACCEPTED.value()));
    }

    private Book mapToBook(Book book, BookDto bookVO, String bookUid, Seller seller) {
        book.setBookUid(bookUid);book.setBookName(bookVO.getBookName());book.setImageSource("/path/to/bookImage");
        book.setAuthors(bookVO.getAuthors());book.setPublication(bookVO.getPublication());book.setKeywords(bookVO.getKeywords());
        book.setStock(bookVO.getStock());book.setCost(bookVO.getCost());book.setDiscount(bookVO.getDiscount());
        book.setBookDescription(bookVO.getBookDescription());book.setBookSeller(seller);
        if (bookVO.getIsLended() != null && bookVO.getIsLended()) {
            var lendBook = new LendBook(); lendBook.setLendableBook(book); lendBook.setDuration(bookVO.getLendDuration()); 
            lendBook.setLendBookPrimaryKeyId(bookUid); lendBook.setLendCost(bookVO.getLendCost());
            book.setLendableBook(lendBook);
        }
        return book;
    }
}
