package com.loan.app.vo;

import javax.persistence.Column;

public class BankDetailsRequestVO {

    private int acctNo;
    private int customerId;
    private String bankName;
    private int annualIncome;
    private String address;
    private int pinCode;
    private String panNO;

    public long getAcctNo() { return acctNo; }

    public void setAcctNo(int acctNo) { this.acctNo = acctNo; }

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

    public String getPanNO() { return panNO; }

    public void setPanNO(String panNO) { this.panNO = panNO; }
}
