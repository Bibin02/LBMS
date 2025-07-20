package com.project.lbms.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Review {

    @EmbeddedId
    private ReviewId reviewId;

    @Column(columnDefinition = "DOUBLE PRECISION CHECK (rating <= 5.0)")
    private double rating;
    
    private String comments;
    
    @ManyToOne
    @MapsId("reviewBookUid")
    @JoinColumn(name = "book_uid")
    private Book reviewBook;

    @ManyToOne
    @MapsId("reviewUserUid")
    @JoinColumn(name = "user_uid")
    private Users reviewUser;
}
