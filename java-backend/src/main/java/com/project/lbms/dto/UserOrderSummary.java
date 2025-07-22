package com.project.lbms.dto;

import java.util.List;

import com.project.lbms.model.Orders;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserOrderSummary {

    private String orderId;
    private String orderStatus;
    private int orderStatusCode;
    private double totalCost;
    private boolean isPaid;

    private List<OrderBook> items;

    public static UserOrderSummary build(Orders order) {
        return new UserOrderSummary(
            order.getOrderId(), order.getOrderStatusMessage(), order.getOrderStatusCode(), 
            order.getTotalAmount(), 
            order.isPaid(), 
            OrderBook.build(order.getOrderCart())
            );
    }

}
