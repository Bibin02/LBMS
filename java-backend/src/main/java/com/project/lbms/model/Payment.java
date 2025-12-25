package com.project.lbms.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @Column(name = "payment_amount", nullable = false)
    private double paymentAmount;
    @Column(name = "payment_date", nullable = false)
    private LocalDateTime paymentDate;

    @OneToOne
    @JoinColumn(name = "order_uid")
    private Orders paymentOrder;

    @ManyToOne
    @JoinColumn(name = "user_uid")
    private Users paymentUser;

}
