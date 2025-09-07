package com.project.lbms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.lbms.constants.CartType;
import com.project.lbms.model.Cart;
import com.project.lbms.model.Users;

@Repository
public interface CartRepository extends JpaRepository<Cart,String>{

    Page<Cart> findByCartUserAndCartType(Users user, CartType cartType, Pageable pageable);

}
