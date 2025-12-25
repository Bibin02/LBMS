package com.project.lbms.dto;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.lbms.model.LendBook;
import com.project.lbms.util.JsonConverter;

import jakarta.persistence.Convert;
import lombok.RequiredArgsConstructor;
import lombok.Data;

@Data
@SuppressWarnings("unused")
@RequiredArgsConstructor
public class SellerBookVO {
    private final String bookUid;
    private final String bookName;
    private final String imageSource;
    private final String authors;
    @JsonIgnore
    private final LendBook lendableBook;
    private Boolean isLended;
    private Long lendDuration;
    private Double lendCost;
    private final String publication;
    private final String keywords;
    private final Double cost;
    private final Integer discount;
    private final Integer stock;
    @Convert(converter = JsonConverter.class)
    private final Map<String, Object> bookDescription;

    public List<String> getKeywords(){
        return List.of(this.keywords.split(","));
    }
}
