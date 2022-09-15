package com.loan.app.vo;

import com.loan.app.constant.LoanAppConstant;

import java.util.Date;

public class LoanOfferVO {

    private int loanId;
    private int interestRate;

    public int getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(int interestRate) {
        this.interestRate = interestRate;
    }

    private int applicationId;
    private int loanAmount;

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private int interestAmount;
    private int tenure;
    private String loanType = LoanAppConstant.PROD_CODE;
    private Date loanCreateDate;

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
