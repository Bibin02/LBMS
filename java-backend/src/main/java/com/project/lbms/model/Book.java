package com.project.lbms.model;

import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.lbms.util.JsonConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "book_uid")
    private String bookUid;

    @Column(name = "book_name", nullable = false)
    private String bookName;

    @Column(name = "image_source")
    private String imageSource;

    @Column(name = "author_names")
    private String authors;

    @Column(name = "publication_name")
    private String publication;

    @Column(name = "keywords")
    private String keywords;

    @Column(name = "stock", nullable = false)
    private int stock;

    @Column(name = "cost", nullable = false)
    private double cost;

    @Column(name = "discount")
    private int discount;

    @Column(name = "book_description")
    @Convert(converter = JsonConverter.class)
    private Map<String, Object> bookDescription;

    @OneToOne(mappedBy = "lendableBook", cascade = CascadeType.ALL, orphanRemoval = true)
    private LendBook lendableBook;

    @OneToMany(mappedBy = "reviewBook")
    private Set<Review> reviews;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_uid", nullable = false)
    private Seller bookSeller;

    @OneToMany(mappedBy = "cartBookIdObject")
    @JsonIgnore
    private Set<CartBook> bookFoundInCarts;

}
