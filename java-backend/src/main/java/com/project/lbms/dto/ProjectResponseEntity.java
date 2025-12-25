package com.project.lbms.dto;

import lombok.Data;

@Data
public class ProjectResponseEntity {
    
    private String message;
    private int httpStatusCode;

    private ProjectResponseEntity(String message, int httpStatusCode){
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

    public static ProjectResponseEntity getProjectResponseEntity(String message, int code) {
        return new ProjectResponseEntity(message, code);
    }
}
