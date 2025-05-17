package com.project.lbms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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

    @Column(name = "author_names")
    private String authors;

    @Column(name = "publication_name")
    private String publication;

    @Column(name = "keywords")
    private String keywords;

    @Column(name = "stock")
    private String stock;

    @Column(name = "discount")
    private String discount;

    @Column(name = "book_description")
    private String bookDescription;
}
