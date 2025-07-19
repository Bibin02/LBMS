package com.project.lbms.util;

import org.springframework.http.ResponseEntity;

import com.project.lbms.dto.LbmsExceptionDto;
import com.project.lbms.exception.LbmsException;

public class LbmsResponseEntityBuilder {
    protected ResponseEntity<Object> getResponseEntityOk(Object response){
        return ResponseEntity.ok(response);
    }

    protected ResponseEntity<Object> getLbmsErrorResponse(LbmsException exception){
        return ResponseEntity.status(exception.getHttpStatusCode()).body(LbmsExceptionDto.getLbmsExceptionDto(exception));
    }
}
