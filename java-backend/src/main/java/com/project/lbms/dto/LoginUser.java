package com.project.lbms.dto;

import com.project.lbms.constants.LbmsConstants;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginUser {
    @NotNull(message = "userId " + LbmsConstants.FIELD_IS_REQUIRED)
    @Email(message = "Invalid userId")
    private String userId;
    @NotNull(message = "password " + LbmsConstants.FIELD_IS_REQUIRED)
    private String password;
}
