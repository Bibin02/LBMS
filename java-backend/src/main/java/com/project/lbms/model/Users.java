package com.project.lbms.model;

import java.util.Set;

import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.project.lbms.constants.ApplicationRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {
    @Id
    @Column(name = "user_uid")
    private String userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(nullable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    private String pass;

    @Column(name = "user_address", nullable = false)
    private String userAddress;

    @Column(name = "user_description")
    private String userDescription;

    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "user_role", nullable = false)
    private ApplicationRole role;


    @OneToMany(mappedBy = "reviewUser")
    private Set<Review> reviews;

    @OneToMany(mappedBy = "lendedUserUid")
    private Set<LendUserBook> lendedBooks;

    @OneToMany(mappedBy = "cartUser")
    private Set<Cart> userCarts;

    @OneToMany(mappedBy = "paymentUser", fetch = FetchType.LAZY)
    private Set<Payment> payment;
}
