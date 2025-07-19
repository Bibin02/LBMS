package com.project.lbms.model;

import java.util.Map;
import java.util.Set;

import com.project.lbms.util.JsonConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Column(name = "book_description", columnDefinition = "jsonb")
    @Convert(converter = JsonConverter.class)
    private Map<String, Object> bookDescription;

    @OneToOne(mappedBy = "lendableBook")
    private LendBook lendableBook;

    @OneToMany(mappedBy = "reviewBook")
    private Set<Review> reviews;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_uid", nullable = false)
    private Seller bookSeller;

    @ManyToMany(mappedBy = "cartBooks")
    private Set<Cart> bookFoundInCarts;

}
