package com.project.lbms.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.lbms.constants.LbmsConstants;
import com.project.lbms.dto.UserOrderSummary;
import com.project.lbms.dto.PaginatedResponse;
import com.project.lbms.exception.LbmsException;
import com.project.lbms.model.Orders;
import com.project.lbms.repository.CartBookRepository;
import com.project.lbms.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderService {
    private OrderRepository orderRepository;
    private CartBookRepository cartBookRepository;
    private static final String ORDER_SERVICE_STR = "OrderService";

    public OrderService(OrderRepository orderRepository, CartBookRepository cartBookRepository){
        this.orderRepository = orderRepository;
        this.cartBookRepository = cartBookRepository;
    }

    @Transactional
    public UserOrderSummary getOrder(String orderId) throws LbmsException{
        log.info("{} getUserOrder {}", ORDER_SERVICE_STR, orderId);
        Orders order = orderRepository.findById(orderId).orElseThrow(
                ()-> new LbmsException(HttpStatus.NOT_FOUND, LbmsConstants.ORDER_NOT_FOUND + orderId));
        return UserOrderSummary.build(order, cartBookRepository.findByIdBookCartUid(order.getOrderCart().getCartId()));
    }

    public PaginatedResponse getUserOrders(String userId, int pageNumber) throws LbmsException{
        log.info("{} getUserOrder {}", ORDER_SERVICE_STR, userId);
        return PaginatedResponse.build(
            orderRepository.findAllUserOrders(userId, PageRequest.of(pageNumber, LbmsConstants.PAGE_SIZE)));
    }
}
