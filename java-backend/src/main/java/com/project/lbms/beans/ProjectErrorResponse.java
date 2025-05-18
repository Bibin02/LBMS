package com.project.lbms.beans;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class ProjectErrorResponse {
    private int errorCode;
    private String message;
}
