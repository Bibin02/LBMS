package com.project.lbms.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@Embeddable
public class ReviewId implements Serializable{
    private String reviewBookUid;
    private String reviewUserUid;
}
