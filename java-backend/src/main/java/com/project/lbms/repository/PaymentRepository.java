package com.project.lbms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.lbms.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String>{

}
