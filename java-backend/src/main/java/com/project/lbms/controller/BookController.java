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
import com.project.lbms.dto.BookVO;
import com.project.lbms.dto.ProjectResponseEntity;
import com.project.lbms.exception.LbmsException;
import com.project.lbms.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1")
@Tag(name = "Book Controller", description = "Endpoints related to book handling")
public class BookController{

    private BookService bookService;
    private static final String BOOK_CONTROLLER_STR = "BookController";

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @Operation(
        summary = "Get Master Book's Data",
        description = "Get Detailed view of a particular Book and their relations  (internal use only)",
        responses = @ApiResponse(
                responseCode = "200",
                description = "Successfully retrieved Master Book's Data",
                content = @Content(
                    mediaType = "application/json"
                )
            )
    )
    @GetMapping(path = "/masterBook/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getMasterBook(@PathVariable String id) throws LbmsException{
        log.info( "{} getMasterBook {}",BOOK_CONTROLLER_STR, id);
        return ResponseEntity.ok(bookService.findMasterBookById(id));
    }
    

    @Operation(
        summary = "Get All Master Books Data",
        description = "Fetches all Books from the database (internal use only)",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Successfully retrieved Master Books",
                content = @Content(
                    mediaType = "application/json"
                ))})
    @GetMapping(path = "/masterBooks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getMasterBooks(
        @RequestParam(required = false, defaultValue = "0") int pageNumber) 
        throws LbmsException{
        log.info( "{} getMasterBooks",BOOK_CONTROLLER_STR);
        return ResponseEntity.ok(bookService.findAllMasterBooks(pageNumber));
    }


    @Operation(
        summary = "Get a particular Book for View",
        description = "Fetches particular book from the database",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Successfully retrieved the book",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = BookVO.class)))})
    @GetMapping(path = "/book/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getBook(@PathVariable String id) throws LbmsException{
        log.info( "{} getBook {}",BOOK_CONTROLLER_STR, id);
        return ResponseEntity.ok(bookService.findBookById(id));
    }
    

    @Operation(
        summary = "Get all Books for the Recomendation",
        description = "Fetches particular book from the database",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Successfully retrieved recommended books",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = BookVO.class)))})
    @GetMapping(path = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getBooks(
        @RequestParam(required = false, defaultValue = "0") int pageNumber) 
        throws LbmsException{
        log.info( "{} getBooks",BOOK_CONTROLLER_STR);
        return ResponseEntity.ok(bookService.findAllBooks(pageNumber));
    }


    @Operation(
        summary = "Adds the given Book",
        description = "Seller adds the book to be sold.",
        responses = {
            @ApiResponse(
                responseCode = "201",
                content = @Content(
                    schema = @Schema(implementation = ProjectResponseEntity.class)))})
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


    @Operation(
        summary = "Updates the given Book",
        description = "Seller updates the book to be sold.",
        responses = {
            @ApiResponse(
                responseCode = "201",
                content = @Content(
                    schema = @Schema(implementation = ProjectResponseEntity.class)))})
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
    