package com.project.lbms.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.lbms.util.LbmsResponseEntityBuilder;

@RestControllerAdvice
public class ProjectErrorResponse extends LbmsResponseEntityBuilder{

    @ExceptionHandler(LbmsException.class)
    public ResponseEntity<Object> getErrorResponse(LbmsException lbmsException){
        return getLbmsErrorResponse(lbmsException);
    }
}
