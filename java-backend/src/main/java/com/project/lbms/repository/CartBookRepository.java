package com.project.lbms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.lbms.model.CartBook;
import com.project.lbms.model.CartBookId;

public interface CartBookRepository extends JpaRepository<CartBook, CartBookId>{

    String CART_BOOK_QUERY = "SELECT * FROM cart_book where cart_uid = :cartUid";
    String CART_UID = "cartUid";

    @Query(value = CART_BOOK_QUERY, nativeQuery = true)
    List<CartBook> findByIdBookCartUid(@Param(CART_UID) String cartUid);
}
