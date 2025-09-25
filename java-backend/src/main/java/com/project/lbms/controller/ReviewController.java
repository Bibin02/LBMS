package com.project.lbms.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.lbms.dto.ReviewRequest;
import com.project.lbms.exception.LbmsException;
import com.project.lbms.service.ReviewService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/review")
public class ReviewController{

    private ReviewService reviewSevice;
    private static final String REVIEW_CONTROLLER_STR = "ReviewController";

    public ReviewController(ReviewService reviewService){
        this.reviewSevice = reviewService;
    }

    @GetMapping(path = "/{bookUid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getBookReviews(
        @PathVariable String bookUid,
        @RequestParam(required = false, defaultValue = "0") Integer pageNumber
        ){
        log.info("{} getBookReviews {}", REVIEW_CONTROLLER_STR, bookUid);
        return ResponseEntity.ok(reviewSevice.getBookReviews(bookUid, pageNumber));
    }

    @GetMapping(path = "/{bookUid}/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUserReview(
        @PathVariable String bookUid
        // Decrypted token will have userId and role filtered based on role via method level security
    ) throws LbmsException{
        String userUid = "john@example.com"; // HardCoded user
        log.info("{} getUserReview {} for Book Id {}", REVIEW_CONTROLLER_STR, userUid, bookUid);
        return ResponseEntity.ok().body(
            reviewSevice.getUserReview(bookUid, userUid));
    }

    @PostMapping(path = "/{bookUid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addBookReview(
        @PathVariable String bookUid,
        @Valid @RequestBody ReviewRequest reviewDto
        // Decrypted token will have userId and role filtered based on role via method level security
    ) throws Exception{
        String userUid = "john@example.com"; // HardCoded user
        log.info("{} addBookReview {} for Book Id {}", REVIEW_CONTROLLER_STR, userUid, bookUid);
        return reviewSevice.addOrUpdateBookReview(bookUid, userUid, reviewDto);
    }

    
}
