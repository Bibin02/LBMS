package com.project.lbms.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Payment {

    @Id
    @Column(name = "payment_uid")
    private String paymentId;

    @Column(name = "payment_status")
    private String paymentStatus;
    @Column(name = "payment_amount")
    private double paymentAmount;
    @Column(name = "payment_date")
    private Date paymentDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_uid")
    private Orders paymentOrder;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_uid")
    private Users paymentUser;

}
