package com.project.lbms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private int reviewId;
    private double rating;
    private String comments;



    @ManyToOne
    @JoinColumn(name = "book_uid")
    private Book reviewBook;

    @ManyToOne
    @JoinColumn(name = "user_uid")
    private Users reviewUser;
}
