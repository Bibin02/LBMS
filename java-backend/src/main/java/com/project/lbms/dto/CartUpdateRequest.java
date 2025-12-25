package com.project.lbms.dto;

import com.project.lbms.constants.LbmsConstants;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CartUpdateRequest {
    @NotBlank(message = "bookUid" + LbmsConstants.FIELD_IS_REQUIRED)
    private String bookUid;
    @Min(1)
    private int quantity;
    private boolean lend;

    public void setLend(boolean lend){
        this.lend = lend;
    }
}
