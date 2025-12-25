package com.project.lbms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.lbms.dto.SalesData;
import com.project.lbms.model.Seller;

public interface SellerRepository extends JpaRepository<Seller, String>{

    String WEEKLY_SALES = 
                "SELECT SUM(cb.book_count)::int as books_sold," +
                "SUM(cb.book_count * b.cost) as per_day_revenue," +
                "TO_CHAR(o.order_time, 'Day')" +
                "as day_of_week " +
                "FROM cart_book cb " +
                "JOIN orders o ON " +
                "cb.cart_uid = o.order_uid " +
                "JOIN book b ON " +
                "b.book_uid = cb.book_uid " +
                "WHERE " +
                "o.is_delivered = true " +
                "AND order_time >= date_trunc('week', CURRENT_DATE) - INTERVAL '7 days' " +
                "AND o.order_time < date_trunc('week', CURRENT_DATE) " +
                "GROUP BY " +
                "day_of_week;";

    @Query(value = WEEKLY_SALES, nativeQuery = true)
    List<SalesData> getWeeklySales(String sellerUid);

}
