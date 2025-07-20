package com.project.lbms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.lbms.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,String>{

}
