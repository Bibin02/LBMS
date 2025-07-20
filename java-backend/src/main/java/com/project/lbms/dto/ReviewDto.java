package com.project.lbms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewDto {
    private String userUid;
    private String comments;
    private double rating;

}
