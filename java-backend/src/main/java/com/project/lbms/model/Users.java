package com.project.lbms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @Column(name = "uid")
    private String userId;

    @Column(name = "uname")
    private String userName;

    private String pass;

    @Column(name = "is_seller")
    private Boolean isSeller;

    @Column(name = "address")
    private String userAddress;

    @Column(name = "description")
    private String userDescription;

    @Override
    public String toString() {
        return "Users [userId=" + userId + ", userName=" + userName + ", pass=" + pass + ", isSeller=" + isSeller
                + ", userAddress=" + userAddress + ", userDescription=" + userDescription + "]";
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Boolean getIsSeller() {
        return isSeller;
    }

    public void setIsSeller(Boolean isSeller) {
        this.isSeller = isSeller;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }
    
}
