package com.project.lbms.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Seller {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "seller_uid")
    private String sellerId;

    @Column(name = "seller_name", nullable = false)
    private String sellerName;

    @Column(nullable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    private String pass;

    @Column(name = "seller_address", nullable = false)
    private String sellerAddress;

    @Column(name = "seller_description")
    private String sellerDescription;

    @Column(name = "books_sold_count", columnDefinition = "INTEGER DEFAULT 0")
    private int bookSoldCount = 0;
    
    @Column(columnDefinition = "DOUBLE PRECISION DEFAULT 0.0")
    private double earnings = 0.0;

    @OneToMany(mappedBy = "bookSeller", cascade = CascadeType.ALL)
    private Set<Book> saleBooks;

}
