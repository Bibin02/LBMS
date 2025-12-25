package com.project.lbms.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Orders {

    @Id
    @Column(name = "order_uid")
    private String orderId;

    @OneToOne @MapsId
    @PrimaryKeyJoinColumn(name = "order_uid")
    private Cart ordeCart;

    @Column(name = "order_status_code")
    private Integer orderStatusCode;
    @Column(name = "order_status_message")
    private String orderStatusMessage;
    @Column(name = "total_amount")
    private double totalAmount;
    @Column(name = "order_time", nullable = false)
    private LocalDateTime orderTime;
    @Column(name = "is_paid")
    private boolean isPaid;
    @Column(name = "is_delivered")
    private boolean isDelivered;

    @OneToOne(mappedBy = "paymentOrder")
    private Payment payment;

}
