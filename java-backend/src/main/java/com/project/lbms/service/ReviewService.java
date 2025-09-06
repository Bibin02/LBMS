package com.project.lbms.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.project.lbms.constants.LbmsConstants;
import com.project.lbms.dto.PaginatedResponse;
import com.project.lbms.repository.ReviewRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReviewService {
    
    private ReviewRepository reviewRepository;
    private static final String REVIEW_SERVICE_STR = "ReviewService";

    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    public PaginatedResponse getBookReviews(String bookUid, Integer pageNumber){
        log.info("{} getBookReviews {}", REVIEW_SERVICE_STR, bookUid);
        return PaginatedResponse.build(
            reviewRepository.findByReviewBookUid(bookUid, 
            PageRequest.of(pageNumber, LbmsConstants.PAGE_SIZE)));
    }
}
