package com.project.lbms.dto;

import com.project.lbms.constants.LbmsConstants;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateUserDto {
    @NotNull(message = "userName" + LbmsConstants.FIELD_IS_REQUIRED)
    private String userName;
    @NotNull(message = "address" + LbmsConstants.FIELD_IS_REQUIRED)
    private String address;
    private String about;
}
