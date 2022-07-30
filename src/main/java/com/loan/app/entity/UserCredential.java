package com.loan.app.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_credential", schema = "")
public class UserCredential implements Serializable {
    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    String userId;

    @Column(name= "user_pass")
    String userPassword;

    @Column(name = "user_role")
    String userRole;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
