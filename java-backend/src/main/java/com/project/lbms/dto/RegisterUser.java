package com.project.lbms.dto;

import com.project.lbms.constants.ApplicationRole;
import com.project.lbms.constants.LbmsConstants;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegisterUser {

    @NotNull(message = "userId" + LbmsConstants.FIELD_IS_REQUIRED)
    @Email(message = "Invalid Email")
    private String userId;
    @NotNull(message = "password" + LbmsConstants.FIELD_IS_REQUIRED)
    private String password;
    @NotNull(message = "address" + LbmsConstants.FIELD_IS_REQUIRED)
    private String address;
    @Pattern(regexp = LbmsConstants.ROLE_CONSTRAIN_REGEX, message = "Invalid Role")
    private String role = ApplicationRole.BUYER.toString();
    private String userName;
    private String about;
}
