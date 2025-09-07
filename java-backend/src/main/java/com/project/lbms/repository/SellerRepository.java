package com.project.lbms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.lbms.model.Seller;

public interface SellerRepository extends JpaRepository<Seller, String>{

    String WEEKLY_SALES = "SELECT UNNEST(ARRAY[2.2, 3.3, 4.4, 9.0, 10]) as sales";

    @Query(value = WEEKLY_SALES, nativeQuery = true)
    List<Double> getWeeklySales(String sellerUid);

}
