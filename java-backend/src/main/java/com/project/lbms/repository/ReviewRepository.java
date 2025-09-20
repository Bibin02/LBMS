package com.project.lbms.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.lbms.dto.ReviewDto;
import com.project.lbms.model.Review;
import com.project.lbms.model.ReviewId;

@Repository
public interface ReviewRepository extends JpaRepository<Review, ReviewId>{

    String BOOK_RATING_QUERY = "SELECT AVG(rating) as average_rating FROM review  WHERE book_uid = :bookUid";
    String BOOK_UID = "bookUid";

    @Query("SELECT new com.project.lbms.dto.ReviewDto(r.reviewUser.userId, r.comments, r.rating) "
            +" FROM Review r WHERE r.reviewId.reviewBookUid = :bookUid")
    Page<ReviewDto> findByReviewBookBookUid(@Param(BOOK_UID) String bookUid, Pageable pageable);

    @Query(value = BOOK_RATING_QUERY, nativeQuery = true)
    Optional<Double> findBookRating(@Param(BOOK_UID) String bookUid);
}
