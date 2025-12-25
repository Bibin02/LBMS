package com.project.lbms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class CartBookId {

    @Column(name = "cart_uid")
    private String bookCartUid;
    @Column(name = "cart_book_uid")
    private String cartBookUid;
}
