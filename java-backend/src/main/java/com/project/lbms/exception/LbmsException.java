package com.project.lbms.exception;

import org.springframework.http.HttpStatusCode;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class LbmsException extends Exception{
    private final HttpStatusCode httpStatusCode;
    private final String message;
    
    public LbmsException(HttpStatusCode statusCode, String message){
        this.httpStatusCode = statusCode;
        this.message = message;
    }
}
