package com.loan.app.vo;

import com.loan.app.constant.LoanAppConstant;

import java.util.Date;

public class ApplicationRequestVO {
//    private BankDetailsRequestVO bankDetailsRequestVO;
//
//    public BankDetailsRequestVO getBankDetailsRequestVO() {
//        return bankDetailsRequestVO;
//    }
//
//    public void setBankDetailsRequestVO(BankDetailsRequestVO bankDetailsRequestVO) {
//        this.bankDetailsRequestVO = bankDetailsRequestVO;
//    }

    private int applicationId;

    public int getRequestedLoanAmt() {
        return requestedLoanAmt;
    }

    public void setRequestedLoanAmt(int requestedLoanAmt) {
        this.requestedLoanAmt = requestedLoanAmt;
    }

    private int requestedLoanAmt;

    private int customerId;
    private final String prodCode = LoanAppConstant.PROD_CODE;
    private Date createDate;
    private Date updateDate;

    private int accountNumber;

    private String bankName;

    private int annualIncome;

    private String panNumber;


    public int getApplicationId() {
        return applicationId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getProdCode() {
        return prodCode;
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
}

