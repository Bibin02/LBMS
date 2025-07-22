package com.project.lbms.dto;

import java.util.List;

import com.project.lbms.model.Orders;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserOrder {

    private String orderId;
    private String orderStatus;
    private double prize;
    private int totalBooks;
    private int lendBooksCount;
    private long orderTime;

    public static List<UserOrder> build(Orders order) {
        return List.of(new UserOrder(
            order.getOrderId(), 
            order.getOrderStatusMessage(), 
            order.getTotalAmount(), 
            0, 
            0, 
            order.getOrderTime().getTime()
            ));
    }

}
