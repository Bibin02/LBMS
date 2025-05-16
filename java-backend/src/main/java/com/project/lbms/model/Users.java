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
    @Column(name = "uid")
    private String userId;

    @Column(name = "uname")
    private String userName;

    @JsonProperty(access = Access.WRITE_ONLY)
    private String pass;

    @Column(name = "is_seller")
    private Boolean isSeller;

    @Column(name = "address")
    private String userAddress;

    @Column(name = "description")
    private String userDescription;
}
