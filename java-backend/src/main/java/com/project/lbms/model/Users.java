package com.project.lbms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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

    @Column(name = "user_name")
    private String userName;

    @JsonProperty(access = Access.WRITE_ONLY)
    private String pass;

    @Column(name = "is_seller")
    private Boolean isSeller;

    @Column(name = "user_address")
    private String userAddress;

    @Column(name = "user_description")
    private String userDescription;
}
