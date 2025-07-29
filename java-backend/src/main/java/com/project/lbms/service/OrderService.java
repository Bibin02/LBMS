package com.project.lbms.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.lbms.constants.LbmsConstants;
import com.project.lbms.dto.UserOrderSummary;
import com.project.lbms.dto.UserOrder;
import com.project.lbms.exception.LbmsException;
import com.project.lbms.model.Cart;
import com.project.lbms.model.Orders;
import com.project.lbms.repository.CartRepository;
import com.project.lbms.repository.OrderRepository;
import com.project.lbms.repository.UsersRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderService {
    private OrderRepository orderRepository;
    private UsersRepository usersRepository;
    private CartRepository cartRepository;
    private static final String ORDER_SERVICE_STR = "OrderService";

    public OrderService(OrderRepository orderRepository, UsersRepository usersRepository, CartRepository cartRepository){
        this.orderRepository = orderRepository;
        this.usersRepository = usersRepository;
        this.cartRepository = cartRepository;
    }

    @Transactional
    public UserOrderSummary getOrder(String orderId) throws LbmsException{
        log.info("{} getUserOrder {}", ORDER_SERVICE_STR, orderId);
        Orders order = orderRepository.findById(orderId).orElseThrow(
                ()-> new LbmsException(HttpStatus.NOT_FOUND, LbmsConstants.ORDER_NOT_FOUND + orderId));
        return UserOrderSummary.build(order, order.getOrderCart().getCartBooks());
    }

    public List<UserOrder> getUserOrders(String userId, int pageNumber) throws LbmsException{
        log.info("{} getUserOrder {}", ORDER_SERVICE_STR, userId);
        return UserOrder.build(
            orderRepository.findAllById(
                cartRepository.findByCartUserAndIsOrderedTrue(
                    usersRepository.findById(userId).orElseThrow(
                        ()-> new LbmsException(HttpStatus.NOT_FOUND, LbmsConstants.USER_NOT_FOUND + userId)
                    ), 
                    PageRequest.of(pageNumber, LbmsConstants.PAGE_SIZE)
                ).getContent()
                .stream()
                .map(Cart::getCartId)
                .toList()
            )
        );
    }
}
