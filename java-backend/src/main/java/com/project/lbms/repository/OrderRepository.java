package com.project.lbms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.lbms.model.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders,String>{

}
