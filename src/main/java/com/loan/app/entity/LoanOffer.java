package com.loan.app.entity;

import com.loan.app.constant.LoanAppConstant;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name= "loan_offer",schema = "LoanAppConstant.LOAN_SCHEMA")
public class LoanOffer implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="loan_id")
    private int loanId;

    @Column(name="application_id")
    private int applicationId;

    @Column(name="provider")
    private String provider;

    @Column(name="loan_amount")
    private int loanAmount;

    @Column(name="cal_interest_amount")
    private int interestAmount;

    @Column(name="tenure")
    private int tenure;

    @Column(name="loan_type")
    private String loanType = LoanAppConstant.PROD_CODE;

    @Column(name= "status")
    private String status;


    @Column(name="loan_create_date")
    private Date loanCreateDate;

    @Column(name="interest_rate")
    private int interestRate;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(int interestRate) {
        this.interestRate = interestRate;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(int loanAmount) {
        this.loanAmount = loanAmount;
    }

    public int getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(int interestAmount) {
        this.interestAmount = interestAmount;
    }

    public int getTenure() {
        return tenure;
    }

    public void setTenure(int tenure) {
        this.tenure = tenure;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public Date getLoanCreateDate() {
        return loanCreateDate;
    }

    public void setLoanCreateDate(Date loanCreateDate) {
        this.loanCreateDate = loanCreateDate;
    }
}
