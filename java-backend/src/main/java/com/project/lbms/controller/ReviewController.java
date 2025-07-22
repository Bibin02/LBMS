package com.project.lbms.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.lbms.service.ReviewService;
import com.project.lbms.util.LbmsResponseEntityBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1")
public class ReviewController extends LbmsResponseEntityBuilder{

    private ReviewService reviewSevice;
    private static final String REVIEW_CONTROLLER_STR = "ReviewController";

    public ReviewController(ReviewService reviewService){
        this.reviewSevice = reviewService;
    }

    @GetMapping(path = "/review/{bookUid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getBookReviews(
        @PathVariable String bookUid,
        @RequestParam(required = false, defaultValue = "0") Integer pageNumber
        ){
        log.info("{} getBookReviews {}", REVIEW_CONTROLLER_STR, bookUid);
        return getResponseEntityOk(reviewSevice.getBookReviews(bookUid, pageNumber));
    }

    
}
