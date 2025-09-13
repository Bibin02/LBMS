package com.project.lbms.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.lbms.dto.BookDto;
import com.project.lbms.exception.LbmsException;
import com.project.lbms.service.BookService;
import com.project.lbms.util.LbmsResponseEntityBuilder;

import jakarta.validation.Valid;
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

    @GetMapping(path = "/masterBook/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getMasterBook(@PathVariable String id) throws LbmsException{
        log.info( "{} getMasterBook {}",BOOK_CONTROLLER_STR, id);
        return getResponseEntityOk(bookService.findMasterBookById(id));
    }
    
    @GetMapping(path = "/masterBooks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getMasterBooks(
        @RequestParam(required = false, defaultValue = "0") int pageNumber) 
        throws LbmsException{
        log.info( "{} getMasterBooks",BOOK_CONTROLLER_STR);
        return getResponseEntityOk(bookService.findAllMasterBooks(pageNumber));
    }

    @GetMapping(path = "/book/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getBook(@PathVariable String id) throws LbmsException{
        log.info( "{} getBook {}",BOOK_CONTROLLER_STR, id);
        return getResponseEntityOk(bookService.findBookById(id));
    }
    
    @GetMapping(path = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getBooks(
        @RequestParam(required = false, defaultValue = "0") int pageNumber) 
        throws LbmsException{
        log.info( "{} getBooks",BOOK_CONTROLLER_STR);
        return getResponseEntityOk(bookService.findAllBooks(pageNumber));
    }

    @PostMapping(path = "/book", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addBook(
        @Valid @RequestBody BookDto bookVO
        // Decrypted token will have userId and role filtered based on role via method level security
    ) throws Exception{
        log.info("{} addBook {}", BOOK_CONTROLLER_STR, bookVO.getBookName());
        return bookService.addBook(bookVO, 
        "john@example.com" // Hardcoded Admin user
        );
    }

    @PutMapping(path = "/book/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateBook(
        @Valid @RequestBody BookDto bookDto,
        @PathVariable String id
        // Decrypted token will have userId and role filtered based on role via method level security
    ) throws Exception{
        log.info("{} updateBook {}", BOOK_CONTROLLER_STR, id);
        return bookService.updateBook(bookDto, id);
    }
        
}
    