package com.project.lbms.dto;

import java.util.Map;

import com.project.lbms.constants.LbmsConstants;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookDto {

    @NotNull(message = "bookName" + LbmsConstants.FIELD_IS_REQUIRED)
    private String bookName;
    private String authors;
    private String publication;
    private String keywords;
    @NotNull(message = "cost" + LbmsConstants.FIELD_IS_REQUIRED)
    private Double cost;
    @NotNull(message = "discount" + LbmsConstants.FIELD_IS_REQUIRED)
    private Integer discount;
    @NotNull(message = "stock" + LbmsConstants.FIELD_IS_REQUIRED)
    private Integer stock;
    private Boolean isLended;
    private long lendDuration;
    private double lendCost;
    private Map<String, Object> bookDescription;
}
