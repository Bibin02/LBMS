package com.project.lbms.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
public class ReviewId implements Serializable{
    private String reviewBookUid;
    private String reviewUserUid;
}
