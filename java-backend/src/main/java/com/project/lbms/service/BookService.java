package com.project.lbms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.project.lbms.constants.LbmsConstants;
import com.project.lbms.dto.BookThumbnail;
import com.project.lbms.dto.BookVO;
import com.project.lbms.exception.LbmsException;
import com.project.lbms.model.Book;
import com.project.lbms.repository.BookRepository;
import com.project.lbms.repository.ReviewRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookService {
    
    private BookRepository bookRepository;
    private ReviewRepository reviwRepository;
    private static final String BOOK_SERVICE_STR = "BookService";

    public BookService(BookRepository bookRepository, ReviewRepository reviewRepository){
        this.bookRepository = bookRepository;
        this.reviwRepository = reviewRepository;
    }

    public Book findMasterBookById(String id) throws LbmsException{
        log.info("{} findMasterBookById {}",BOOK_SERVICE_STR, id);
        Book book;
        if((book = bookRepository.findById(id).orElse(null)) == null){
            throw new LbmsException(HttpStatus.NOT_FOUND, "Book Not Found for the given id " + id);
        }
        return book;
    }

    public List<Book> findAllMasterBooks(int pageNumber) {
        log.info("{} findAllMasterBooks",BOOK_SERVICE_STR);
        return bookRepository.findAll(PageRequest.of(pageNumber, LbmsConstants.PAGE_SIZE)).getContent();
    }

    public BookVO findBookById(String id) throws LbmsException{
        log.info("{} findBookById {}",BOOK_SERVICE_STR, id);
        BookVO book;
        if((book = BookVO.build(bookRepository.findById(id).orElse(null))) == null){
            throw new LbmsException(HttpStatus.NOT_FOUND, "Book Not Found for the given id " + id);
        }
        book.setRating(reviwRepository.findBookRating(id).orElse(5.0));
        return book;
    }

    public List<BookThumbnail> findAllBooks(int pageNumber) {
        log.info("{} findAllBooks",BOOK_SERVICE_STR);
        List<BookThumbnail> bookThumbnails = new ArrayList<>();
        bookRepository.findAll(PageRequest.of(pageNumber, LbmsConstants.PAGE_SIZE))
            .stream().forEach(book->{
                BookThumbnail bookThumbnail = BookThumbnail.build(book);
                double rating = reviwRepository.findBookRating(book.getBookUid()).orElse(5.0);
                bookThumbnail.setRating(rating);
                bookThumbnails.add(bookThumbnail);
            });
        return bookThumbnails;
    }
}
