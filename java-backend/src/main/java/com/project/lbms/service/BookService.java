package com.project.lbms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.lbms.model.Book;
import com.project.lbms.repository.BookRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;

    private static final String BOOK_SERVICE_STR = "BookService";

    public Object findBookById(String id) {
        log.info("{} findBookById {}",BOOK_SERVICE_STR, id);
        return bookRepository.findById(id);
    }

    public List<Book> findAllBooks() {
        log.info("{} findAllBooks {}",BOOK_SERVICE_STR);
        return bookRepository.findAll();
    }

}
