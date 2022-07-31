package com.loan.app.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_credential", schema = "loan_application")
public class UserCredential implements Serializable {
    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    String userId;

    @Column(name= "user_password")
    String userPassword;

    @Column(name = "user_role")
    String userRole;

    /*@OneToOne
    @PrimaryKeyJoinColumn
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }*/

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
