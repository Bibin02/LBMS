package com.project.lbms.exception;

import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LbmsExceptionHandler{

    @ExceptionHandler(LbmsException.class)
    public ErrorResponse handleLbmsException(LbmsException lbmsException){
        return ErrorResponse.create(lbmsException, lbmsException.getHttpStatusCode(), lbmsException.getLocalizedMessage());
    }
}