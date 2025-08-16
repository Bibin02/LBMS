package com.project.lbms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class LendUserBookId {
    @Column(name = "lend_book_uid")
    private String lendBookUid;
    @Column(name = "lend_user_uid")
    private String lendUserUid;
}
