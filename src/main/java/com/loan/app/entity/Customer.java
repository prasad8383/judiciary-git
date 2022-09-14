package com.loan.app.entity;

import com.loan.app.constant.LoanAppConstant;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customer", schema = LoanAppConstant.LOAN_SCHEMA)
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customerId;
    @Column(name = "first_name")
    private String fname;
    @Column(name ="last_name")
    private String lanme;
    @Column(name = "middle_name")
    private String mname;
    @Column(name = "email")
    private String emailId;
    @Column(name = "contact_no")
    private String contactNumber;

    @Column(name = "id")
    private int userCredentialId;

    public int getUserCredentialId() {
        return userCredentialId;
    }

    public void setUserCredentialId(int userCredentialId) {
        this.userCredentialId = userCredentialId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLanme() {
        return lanme;
    }

    public void setLanme(String lanme) {
        this.lanme = lanme;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
