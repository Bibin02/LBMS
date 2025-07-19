package com.project.lbms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.lbms.dto.UsersVO;
import com.project.lbms.model.Users;

@Repository
public interface UsersRepository extends JpaRepository <Users, String>{

    @Query(
        value = "SELECT " + 
        " u.user_uid as user_id, u.user_name as user_name, u.user_address as user_address, u.user_description as user_description, "+ 
        " CASE WHEN EXISTS(SELECT 1 from seller s where s.seller_uid = :id) THEN true "+
        " ELSE false END as is_seller "+
        " from users u where u.user_uid = :id", 
        nativeQuery = true)
    Optional<UsersVO> findUserById(@Param("id") String id);
    
}
