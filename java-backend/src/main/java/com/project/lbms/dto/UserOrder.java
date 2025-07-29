package com.project.lbms.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.project.lbms.model.CartBook;
import com.project.lbms.model.Orders;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserOrder {

    private String orderId;
    private String orderStatus;
    private double totalPrize;
    private int totalBooks;
    private int lendBooksCount;
    private long orderTime;

    public static List<UserOrder> build(List<Orders> orders) {
        List<UserOrder> userOrders = new ArrayList<>();
        orders.stream()
            .forEach(order->{
                Set<CartBook> cartBooks = order.getOrderCart().getCartBooks();
                userOrders.add(new UserOrder(
                    order.getOrderId(), 
                    order.getOrderStatusMessage(), 
                    order.getTotalAmount(), 
                    cartBooks.size(), 
                    cartBooks.stream()
                        .map(CartBook::getBookCount)
                        .reduce(0, Integer::sum), 
                    order.getOrderTime().getTime()
                    )
                );
            }

            );
        return userOrders;
    }

}
