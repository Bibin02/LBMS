package com.project.lbms.dto;

import com.project.lbms.exception.LbmsException;

import lombok.Data;

@Data
public class ProjectResponseEntity {
    
    private String message;
    private int httpStatusCode;

    private ProjectResponseEntity(LbmsException lbmsException){
        this.httpStatusCode = lbmsException.getHttpStatusCode().value();
        this.message = lbmsException.getMessage();
    }

    private ProjectResponseEntity(String message, int httpStatusCode){
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

    public static ProjectResponseEntity getProjectErrorResponseEntity(LbmsException lbmsException){
        return new ProjectResponseEntity(lbmsException);
    }

    public static ProjectResponseEntity getProjectResponseEntity(String message, int code) {
        return new ProjectResponseEntity(message, code);
    }
}
