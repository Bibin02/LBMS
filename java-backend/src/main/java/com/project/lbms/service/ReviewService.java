package com.project.lbms.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.lbms.constants.LbmsConstants;
import com.project.lbms.dto.PaginatedResponse;
import com.project.lbms.dto.ProjectResponseEntity;
import com.project.lbms.dto.ReviewDto;
import com.project.lbms.dto.ReviewRequest;
import com.project.lbms.exception.LbmsException;
import com.project.lbms.model.Review;
import com.project.lbms.model.ReviewId;
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
            reviewRepository.findByReviewBookBookUid(bookUid, 
            PageRequest.of(pageNumber, LbmsConstants.PAGE_SIZE)), pageNumber);
    }

    public ReviewDto getUserReview(String bookUid, String userUid) throws LbmsException{
        var reviewId = new ReviewId();
        reviewId.setReviewBookUid(bookUid); reviewId.setReviewUserUid(userUid);
        var review = reviewRepository.findById(reviewId)
            .orElseThrow(() -> new LbmsException(HttpStatus.NOT_FOUND, "Review " + LbmsConstants.NOT_FOUND));
        return new ReviewDto(userUid, review.getComments(), review.getRating());
    }

    @Transactional
    public ResponseEntity<Object> addOrUpdateBookReview(String bookUid, String userUid, ReviewRequest reviewDto) throws URISyntaxException{
        log.info("{} addBookReview {}", userUid, bookUid);
        var reviewId = new ReviewId();
        reviewId.setReviewBookUid(bookUid); reviewId.setReviewUserUid(userUid);
        var userReview = reviewRepository.findById(reviewId).orElse(null);
        if (userReview != null) {
            userReview.setComments(reviewDto.getComments());
            userReview.setRating(reviewDto.getRating().doubleValue());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Review Updated Successfully");
        }
        var newReview = new Review();
        newReview.setComments(reviewDto.getComments());
        newReview.setRating(reviewDto.getRating().doubleValue());
        newReview.setReviewId(reviewId);
        return ResponseEntity.created(
            new URI(String.format("/review/%s/user", bookUid)))
            .body(ProjectResponseEntity
                .getProjectResponseEntity(
                    "Review Posted Successfully", 
                    HttpStatus.CREATED.value()
                ));
    }
}
