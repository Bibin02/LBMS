package com.project.lbms.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "cart_book")
@Data
@NoArgsConstructor
public class CartBook {

    @EmbeddedId
    private CartBookId cartBookId;

    @Column(name = "book_count", columnDefinition = "INTEGER DEFAULT 1")
    private int bookCount;

    @ManyToOne
    @MapsId(value = "cartBookUid")
    @JoinColumn(name = "book_uid")
    private Book cartBookIdObject;

    @ManyToOne
    @MapsId(value = "bookCartUid")
    @JoinColumn(name = "cart_uid")
    private Cart bookCartIdObject;
}
