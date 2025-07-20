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

@Repository
public interface ReviewRepository extends JpaRepository<Review, Review>{
    @Query(value = "SELECT user_uid, comments, rating FROM review WHERE book_uid = :bookUid", nativeQuery = true)
    Page<ReviewDto> findByReviewBookUid(@Param("bookUid") String bookUid, Pageable pageable);

    @Query(value = "SELECT AVG(rating) as average_rating FROM review  WHERE book_uid = :bookUid", nativeQuery = true)
    Optional<Double> findBookRating(@Param("bookUid") String bookUid);
}
