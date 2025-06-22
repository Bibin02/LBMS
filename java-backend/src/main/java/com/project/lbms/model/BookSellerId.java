package com.project.lbms.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class BookSellerId implements Serializable {
    @Column(name = "book_uid")
    private String bookUId;
    @Column(name = "seller_uid")
    private String sellerUId;
}