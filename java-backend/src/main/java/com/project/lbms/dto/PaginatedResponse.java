package com.project.lbms.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import com.project.lbms.exception.LbmsException;
import com.project.lbms.util.ResponseBuilder;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PaginatedResponse {
    private List<? extends Object> data;
    private int pageNumber;
    private int totalPages;
    public static PaginatedResponse build(Page<? extends Object> pageData){
        return new PaginatedResponse(
            pageData.getContent(), 
            pageData.getNumber(),
            pageData.getTotalPages()
        );
    }

    public static PaginatedResponse build(Page<? extends Object> pageData, ResponseBuilder rb) throws LbmsException{
        return new PaginatedResponse(
            rb.build(pageData.getContent()), 
            pageData.getNumber(),
            pageData.getTotalPages()
        );
    }
}
