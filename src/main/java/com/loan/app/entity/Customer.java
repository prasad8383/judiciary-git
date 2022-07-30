package com.loan.app.entity;

import com.loan.app.constant.LoanAppConstant;

import javax.persistence.*;

@Entity
@Table(name = "customer", schema = LoanAppConstant.LOAN_SCHEMA)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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
