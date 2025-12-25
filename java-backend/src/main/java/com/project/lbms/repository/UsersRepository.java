package com.project.lbms.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.lbms.constants.LbmsConstants;
import com.project.lbms.dto.UserLendBookDto;
import com.project.lbms.dto.UsersVO;
import com.project.lbms.model.Users;

@Repository
public interface UsersRepository extends JpaRepository <Users, String>{

    String FIND_USER_BY_ID_QUERY = "SELECT " + 
        " u.user_uid as user_id, u.user_name as user_name, u.user_address as user_address, u.user_description as user_description, "+ 
        " CASE WHEN EXISTS(SELECT 1 from seller s where s.seller_uid = :id) THEN true "+
        " ELSE false END as is_seller "+
        " from users u where u.user_uid = :id";

    String FIND_USER_QUERY = "SELECT " + 
        " u.user_uid as user_id, u.user_name as user_name, u.user_address as user_address, u.user_description as user_description, "+ 
        " CASE WHEN EXISTS(SELECT 1 from seller s where s.seller_uid = u.user_uid) THEN true "+
        " ELSE false END as is_seller "+
        " from users u ";

    String USER_LEND_BOOK_QUERY = "select " +
                "b.book_uid as bookUid, " +
                "b.book_name as bookName, " +
                "b.image_source as imageSource," +
                "lb.duration as lendDuration," +
                "o.order_uid as orderUid," +
                "o.order_time as orderDate " +
                "from " +
                "lend_book lb," +
                "orders o left join cart c on o.order_uid = c.cart_uid," +
                "book b left join lend_user_book lu on b.book_uid = lu.lend_book_uid," +
                "cart_book cb left join book bb on cb.book_uid = bb.book_uid " +
                "where lu.lend_user_uid = :userId";

    @Query(value = FIND_USER_QUERY, countQuery = LbmsConstants.COUNT_QUERY + FIND_USER_QUERY + ")", nativeQuery = true)
    Page<UsersVO> findUsers(Pageable pageable);
    
    @Query(value = FIND_USER_BY_ID_QUERY, nativeQuery = true)
    Optional<UsersVO> findUserById(@Param("id") String id);

    Optional<Users> findByUserIdAndPass(String userUid, String pass);

    @Query(value = USER_LEND_BOOK_QUERY, countQuery = LbmsConstants.COUNT_QUERY + USER_LEND_BOOK_QUERY + ")", nativeQuery = true)
    Page<UserLendBookDto> findUserLendBooks(@Param("userId") String userId, Pageable pageable);
    
}
