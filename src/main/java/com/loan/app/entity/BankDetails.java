package com.loan.app.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="bank_details", schema = "LoanAppConstant.LOAN_SCHEMA")
public class BankDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="acct_no")
    private long acctNo;

    @Column(name="customer_id")
    private int customerId;

    @Column(name="bank_name")
    private String bankName;

    @Column(name="annual_income")
    private int annualIncome;

    @Column(name="pin_code")
    private int pinCode;

    @Column(name="address")
    private String address;

    @Column(name="pan_no")
    private int panNO;

    public long getAcctNo() { return acctNo; }

    public void setAcctNo(long acctNo) { this.acctNo = acctNo; }

    public int getCustomerId() { return customerId; }

    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public String getBankName() { return bankName; }

    public void setBankName(String bankName) { this.bankName = bankName; }

    public int getAnnualIncome() { return annualIncome; }

    public void setAnnualIncome(int annualIncome) { this.annualIncome = annualIncome; }

    public int getPinCode() { return pinCode; }

    public void setPinCode(int pinCode) { this.pinCode = pinCode; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public int getPanNO() { return panNO; }

    public void setPanNO(int panNO) { this.panNO = panNO; }
}
