package com.project.lbms.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.lbms.dto.SellerBookVO;
import com.project.lbms.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,String>{

    Page<Book> findByBookSellerSellerId(String sellerUid, PageRequest pageable);

    Optional<SellerBookVO> findByBookSellerSellerIdAndBookUid(String sellerUid, String bookUid);

}
