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

@Entity(name = "lend_book")
@Data
@NoArgsConstructor
public class LendBook {

    @Id
    @Column(name = "book_uid")
    private String lendBookUid;

    @OneToOne(fetch = FetchType.LAZY) @MapsId
    @PrimaryKeyJoinColumn(name = "book_uid")
    private Book lendableBook;

    @Column(name = "lend_cost")
    private double lendCost;
    private long duration;

    @OneToMany(mappedBy = "lendedBookUid", fetch = FetchType.LAZY)
    private Set<LendUserBook> lendedBooks;
}
