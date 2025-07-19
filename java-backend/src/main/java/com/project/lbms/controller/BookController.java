package com.project.lbms.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.lbms.exception.LbmsException;
import com.project.lbms.service.BookService;
import com.project.lbms.util.LbmsResponseEntityBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1")
public class BookController extends LbmsResponseEntityBuilder{

    private BookService bookService;
    private static final String BOOK_CONTROLLER_STR = "BookController";

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping(
        path = "/book/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getBook(@PathVariable String id) throws LbmsException{
        log.info( "{} getBook {}",BOOK_CONTROLLER_STR, id);
        return getResponseEntityOk(bookService.findBookById(id));
    }
    
    @GetMapping(
        path = "/books",
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getBooks() throws LbmsException{
        log.info( "{} getBooks",BOOK_CONTROLLER_STR);
        return getResponseEntityOk(bookService.findAllBooks());
    }
        
}
    