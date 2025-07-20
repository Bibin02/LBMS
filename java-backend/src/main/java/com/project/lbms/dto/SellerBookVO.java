package com.project.lbms.dto;

import java.util.List;
import java.util.Map;

import com.project.lbms.model.LendBook;
import com.project.lbms.util.JsonConverter;

import jakarta.persistence.Convert;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SellerBookVO {
    private String bookUid;
    private String bookName;
    private String imageSource;
    private String authors;
    private LendBook lendableBook;
    private String publication;
    private String keywords;
    private Double cost;
    private Integer discount;
    private Integer stock;
    @Convert(converter = JsonConverter.class)
    private Map<String, Object> bookDescription;

    public List<String> getKeywords(){
        return List.of(this.keywords.split(","));
    }
}
