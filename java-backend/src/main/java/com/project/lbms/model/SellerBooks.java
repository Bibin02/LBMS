package com.project.lbms.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "seller_books")
@Data
@NoArgsConstructor
public class SellerBooks {

    @EmbeddedId
    private BookSellerId bookSellerId = new BookSellerId();

    @ManyToOne
    @MapsId("bookUId")
    @JoinColumn(name = "book_uid")
    private Book saleBook;

    @ManyToOne
    @MapsId("sellerUId")
    @JoinColumn(name = "seller_uid")
    private Seller seller;

    @Column(name = "books_sold_count")
    private int bookSoldCount;
    private double earnings;
}
