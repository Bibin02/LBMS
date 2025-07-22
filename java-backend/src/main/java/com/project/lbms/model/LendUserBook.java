package com.project.lbms.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "lend_user_book")
@Data
@NoArgsConstructor
public class LendUserBook {

    @EmbeddedId
    private LendUserBookId lendUserBookId = new LendUserBookId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId(value = "lendBookUid")
    @JoinColumn(name = "lend_book_uid")
    private LendBook lendedBookUid;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId(value = "lendUserUid")
    @JoinColumn(name = "lend_user_uid")
    private Users lendedUserUid;

    @Column(name = "total_lend_cost")
    private double totalLendCost;
    @Column(name = "dispatched_date")
    private Date dispatchedDate;
    @Column(name = "total_lend_duration")
    private long totalLendDuration;
}
