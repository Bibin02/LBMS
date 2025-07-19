package com.project.lbms.dto;

import com.project.lbms.exception.LbmsException;

import lombok.Data;

@Data
public class LbmsExceptionDto {
    
    private String message;
    private int httpStatusCode;

    private LbmsExceptionDto(LbmsException lbmsException){
        this.httpStatusCode = lbmsException.getHttpStatusCode().value();
        this.message = lbmsException.getMessage();
    }

    public static LbmsExceptionDto getLbmsExceptionDto(LbmsException lbmsException){
        return new LbmsExceptionDto(lbmsException);
    }
}
