package com.loan.app.entity;

import com.loan.app.constant.LoanAppConstant;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "application", schema = "LoanAppConstant.LOAN_SCHEMA")
public class Application implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private int applicationId;

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getProdCode() {
        return prodCode;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public int getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(int annualIncome) {
        this.annualIncome = annualIncome;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    @Column(name="customer_id")
    private int customerId;

    @Column(name="prod_code")
    private final String prodCode= LoanAppConstant.PROD_CODE;

    @Column(name="create_date")
    private Date createDate;

    @Column(name="update_date")
    private Date updateDate;

    @Column(name = "acct_no")
    private int accountNumber;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "reqested_loan_amt")
    private int requestedLoanAmt;

    public int getRequestedLoanAmt() {
        return requestedLoanAmt;
    }

    public void setRequestedLoanAmt(int requestedLoanAmt) {
        this.requestedLoanAmt = requestedLoanAmt;
    }

    @Column(name = "annual_income")
    private int annualIncome;

    @Column(name = "pan_no")
    private String panNumber;

}
