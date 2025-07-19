package com.project.lbms.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.project.lbms.exception.LbmsException;
import com.project.lbms.model.Book;
import com.project.lbms.repository.BookRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookService {
    
    private BookRepository bookRepository;
    private static final String BOOK_SERVICE_STR = "BookService";

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public Book findBookById(String id) throws LbmsException{
        log.info("{} findBookById {}",BOOK_SERVICE_STR, id);
        Book book;
        if((book = bookRepository.findById(id).orElse(null)) == null){
            throw new LbmsException(HttpStatus.NOT_FOUND, "Book Not Found for the given id " + id);
        }
        return book;
    }

    public List<Book> findAllBooks() {
        log.info("{} findAllBooks",BOOK_SERVICE_STR);
        return bookRepository.findAll();
    }

}
