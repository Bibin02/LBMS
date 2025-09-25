package com.project.lbms.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.lbms.constants.CartType;
import com.project.lbms.constants.LbmsConstants;
import com.project.lbms.dto.UserOrderSummary;
import com.project.lbms.dto.PaginatedResponse;
import com.project.lbms.dto.ProjectResponseEntity;
import com.project.lbms.exception.LbmsException;
import com.project.lbms.model.Orders;
import com.project.lbms.model.Payment;
import com.project.lbms.repository.CartBookRepository;
import com.project.lbms.repository.CartRepository;
import com.project.lbms.repository.OrderRepository;
import com.project.lbms.repository.PaymentRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class OrderService {
    private CartRepository cartRepository;
    private CartBookRepository cartBookRepository;
    private OrderRepository orderRepository;
    private PaymentRepository paymentRepository;
    private SellerService sellerService;
    private static final String ORDER_SERVICE_STR = "OrderService";

    @Transactional
    public UserOrderSummary getOrder(String orderId) throws LbmsException{
        log.info("{} getUserOrder {}", ORDER_SERVICE_STR, orderId);
        Orders order = orderRepository.findById(orderId).orElseThrow(
                ()-> new LbmsException(HttpStatus.NOT_FOUND, LbmsConstants.ORDER_NOT_FOUND + orderId));
        return UserOrderSummary.build(order, cartBookRepository.findByIdBookCartUid(order.getOrderId()));
    }

    public PaginatedResponse getUserOrders(String userId, int pageNumber) throws LbmsException{
        log.info("{} getUserOrder {}", ORDER_SERVICE_STR, userId);
        return PaginatedResponse.build(
            orderRepository.findAllUserOrders(userId, PageRequest.of(pageNumber, LbmsConstants.PAGE_SIZE)), pageNumber);
    }

    @Transactional
    public ResponseEntity<Object> placeOrder(String userId, String cartId) throws LbmsException{
        log.info("{} placeOrder for user {} for cart {}", ORDER_SERVICE_STR, userId, cartId);
        var cart = cartRepository.findById(cartId).orElseThrow(()->
            new LbmsException(HttpStatus.NOT_FOUND, LbmsConstants.CART_NOT_FOUND));

        if (! cart.getCartUser().getUserId().equals(userId)) {
            throw new LbmsException(HttpStatus.UNAUTHORIZED, LbmsConstants.UNAUTHORIZED_USER);
        }

        if (cart.getCartType().equals(CartType.ORDER_CART)) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(
                ProjectResponseEntity.getProjectResponseEntity
                ("Order Already Placed", HttpStatus.ALREADY_REPORTED.value())
            );
        }
        var totalAmount = 0.0;
        for (var cartbook : cartBookRepository.findByIdBookCartUid(cartId)){
            var book = cartbook.getCartBookIdObject();
            if (cartbook.getBookCount() > book.getStock()) {
                throw new LbmsException(HttpStatus.CONFLICT, 
                "Not enough stocks for book "+ book.getBookName());
            }
            if (cartbook.isLended() && book.getLendableBook() != null) {
                throw new LbmsException(HttpStatus.CONFLICT, 
                String.format("Book %s not available for lend ", book.getBookName()));
            }
            totalAmount += book.getCost();
        }

        cart.setCartType(CartType.ORDER_CART);
        var buyerOrder = new Orders();
        buyerOrder.setOrdeCart(cart);
        buyerOrder.setDelivered(false);
        buyerOrder.setOrderStatusCode(100);
        buyerOrder.setOrderStatusMessage("Order Placed");
        buyerOrder.setOrderTime(LocalDateTime.now(ZoneOffset.UTC));
        buyerOrder.setTotalAmount(totalAmount);
        buyerOrder.setPaid(true);
        buyerOrder.setPayment(null);
        
        orderRepository.save(buyerOrder);

        var payment = new Payment();
        payment.setPaymentOrder(buyerOrder);
        payment.setPaymentAmount(totalAmount);
        payment.setPaymentDate(LocalDateTime.now(ZoneOffset.UTC));
        payment.setPaymentId(UUID.randomUUID().toString());
        payment.setPaymentStatus("SUCCESS");
        payment.setPaymentUser(cart.getCartUser());

        buyerOrder.setPayment(paymentRepository.save(payment));

        sellerService.placeSellerOrders(cartId);

        return ResponseEntity
        .ok(ProjectResponseEntity
        .getProjectResponseEntity(
            "Order placed successfully", 
            HttpStatus.OK.value()));
    }
}
