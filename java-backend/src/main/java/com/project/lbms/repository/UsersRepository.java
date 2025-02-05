package com.project.lbms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.lbms.model.Users;

@Repository
public interface UsersRepository extends JpaRepository <Users, Integer>{
    
}
