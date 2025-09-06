package com.project.lbms.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Seller {
    
    @Id
    private String sellerId;
    
    @OneToOne @MapsId
    @PrimaryKeyJoinColumn(name = "seller_uid")
    private Users sellerInfo;

    @Column(name = "books_sold_count", columnDefinition = "INTEGER DEFAULT 0")
    private int bookSoldCount = 0;
    
    @Column(columnDefinition = "DOUBLE PRECISION DEFAULT 0.0")
    private double earnings = 0.0;

    @OneToMany(mappedBy = "bookSeller", fetch = FetchType.LAZY)
    private Set<Book> saleBooks;

}
