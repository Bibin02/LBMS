package com.project.lbms.dto;

import java.math.BigDecimal;

import com.project.lbms.constants.LbmsConstants;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewRequest {
    @NotNull(message = "rating" + LbmsConstants.FIELD_IS_REQUIRED)
    @DecimalMin("1.0")
    @DecimalMax("5.0")
    private BigDecimal rating;
    private String comments;
}
