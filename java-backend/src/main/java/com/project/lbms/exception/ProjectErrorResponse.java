package com.project.lbms.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.lbms.dto.ProjectResponseEntity;

@RestControllerAdvice
public class ProjectErrorResponse{

    @ExceptionHandler(LbmsException.class)
    public ResponseEntity<Object> getErrorResponse(LbmsException lbmsException){
        return ResponseEntity.status(lbmsException.getHttpStatusCode()).body(ProjectResponseEntity.getProjectErrorResponseEntity(lbmsException));
    }
}