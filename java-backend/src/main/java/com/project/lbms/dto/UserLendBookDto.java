package com.project.lbms.dto;

import java.sql.Timestamp;

public interface UserLendBookDto {
    public String getBookUid();
    public String getBookName();
    public String getImageSource();
    public Long getLendDuration();
    public String getOrderUid();
    public Timestamp getOrderDate();
}
