package com.project.lbms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.lbms.constants.LbmsConstants;
import com.project.lbms.dto.UserOrder;
import com.project.lbms.model.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders,String>{

    String USER_ORDERS_QUERY = "SELECT " + 
                " o.order_uid as order_id ," + 
                " o.order_status_message as order_status, " + 
                " o.total_amount as total_prize," +
                " (EXTRACT(EPOCH FROM o.order_time) * 1000):: BIGINT as order_time," + 
                " (SELECT SUM(cb.book_count) FROM cart_book cb where cb.cart_uid = o.order_uid) as total_books, " + 
                " (SELECT COUNT(cb.is_lended) FROM cart_book cb where cb.cart_uid = o.order_uid AND cb.is_lended = true) as lend_books_count " +
                " from orders o " + 
                " JOIN cart c1 ON " + 
                " o.order_uid = c1.cart_uid" + 
                " WHERE " + 
                " c1.user_uid = :userUid ";
    String USER_UID = "userUid";

    @Query(value = USER_ORDERS_QUERY, countQuery = LbmsConstants.COUNT_QUERY + USER_ORDERS_QUERY + ")", nativeQuery = true)
    Page<UserOrder> findAllUserOrders(@Param(USER_UID) String userUid, Pageable pageable);

}
