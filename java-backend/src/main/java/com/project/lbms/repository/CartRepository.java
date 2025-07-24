package com.project.lbms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.lbms.model.Cart;
import com.project.lbms.model.Users;

@Repository
public interface CartRepository extends JpaRepository<Cart,String>{

    List<Cart> findByCartUserAndIsOrderedFalse(Users user);

}
