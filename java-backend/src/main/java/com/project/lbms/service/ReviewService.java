package com.project.lbms.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.project.lbms.constants.LbmsConstants;
import com.project.lbms.dto.ReviewDto;
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

    public List<ReviewDto> getBookReviews(String bookUid, Integer pageNumber){
        log.info("{} getBookReviews {}", REVIEW_SERVICE_STR, bookUid);
        Page<ReviewDto> pagedReviews = reviewRepository.findByReviewBookUid(bookUid, 
            PageRequest.of(pageNumber, LbmsConstants.PAGE_SIZE));
        return pagedReviews.getContent();
    }
}
