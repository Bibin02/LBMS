package com.project.lbms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.lbms.service.BookService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1")
public class BookController {

    @Autowired
    private BookService bookService;

    private static final String BOOK_CONTROLLER_STR = "BookController";

    
    @GetMapping(
        path = "/book/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getBook(@PathVariable String id){
        log.info( "{} getBook {}",BOOK_CONTROLLER_STR, id);
        return bookService.findBookById(id);
    }
    
    @GetMapping(
        path = "/books",
        produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getBooks(){
        log.info( "{} getBooks",BOOK_CONTROLLER_STR);
        return bookService.findAllBooks();
    }
        
}
    