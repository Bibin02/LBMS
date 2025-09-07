package com.project.lbms.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.lbms.dto.SellerBookVO;
import com.project.lbms.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,String>{

    String TOTAL_SELLER_BOOKS = "SELECT SUM(b.stock) FROM Book b WHERE b.bookSeller.sellerId = :sellerUid";
    String SELLER_UID = "sellerUid";

    Page<Book> findByBookSellerSellerId(String sellerUid, PageRequest pageable);

    Optional<SellerBookVO> findByBookSellerSellerInfoUserIdAndBookUid(String sellerUid, String bookUid);

    int countByBookSellerSellerInfoUserId(String sellerUid);

    @Query(TOTAL_SELLER_BOOKS)
    int sellerTotalBooks(@Param(SELLER_UID) String sellerUid);

}
