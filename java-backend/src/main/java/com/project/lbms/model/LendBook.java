package com.project.lbms.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "lend_book")
@Data
@NoArgsConstructor
public class LendBook {

    @Id
    private String lendBookPrimaryKeyId;

    @OneToOne
    @JoinColumn(name = "lend_book_uid")
    private Book lendableBook;

    @Column(name = "lend_cost")
    private double lendCost;
    private long duration;

    @OneToMany(mappedBy = "lendedBookUid")
    private Set<LendUserBook> lendedBooks;
}
