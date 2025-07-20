package com.project.lbms.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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


    @OneToMany(mappedBy = "reviewUser", fetch = FetchType.LAZY)
    private Set<Review> reviews;

    @OneToMany(mappedBy = "lendedUserUid", fetch = FetchType.LAZY)
    private Set<LendUserBook> lendedBooks;

    @OneToMany(mappedBy = "cartUser", fetch = FetchType.LAZY)
    private Set<Cart> userCarts;

    @OneToOne(mappedBy = "paymentUser", fetch = FetchType.LAZY)
    private Payment payment;
}
