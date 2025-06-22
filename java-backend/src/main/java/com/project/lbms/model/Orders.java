package com.project.lbms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Orders {

    @Id
    @Column(name = "order_uid")
    private String orderId;

    @Column(name = "order_status_code")
    private String orderStatusCode;
    @Column(name = "order_status_message")
    private String orderStatusMessage;
    @Column(name = "total_amount")
    private double totalAmount;
    @Column(name = "is_paid")
    private boolean isPaid;
    @Column(name = "is_delivered")
    private boolean isDelivered;

    @OneToOne
    @JoinColumn(name = "cart_uid")
    private Cart orderCart;

    @OneToOne(mappedBy = "paymentOrder")
    private Payment payment;

}
