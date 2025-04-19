package com.project.lbms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Users {
    @Id
    private String uid;
    private String uname;
    private String pass;
    public String getUid() {
        return uid;
    }
    @Override
    public String toString() {
        return "Users [uid=" + uid + ", uname=" + uname + ", pass=" + pass + "]";
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    
}
