package com.project.lbms.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lend_book_uid")
    private Book lendableBook;

    @Column(name = "lend_cost")
    private double lendCost;
    private long duration;

    @OneToMany(mappedBy = "lendedBookUid", fetch = FetchType.LAZY)
    private Set<LendUserBook> lendedBooks;
}
