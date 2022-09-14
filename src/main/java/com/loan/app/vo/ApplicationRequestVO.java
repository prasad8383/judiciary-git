package com.loan.app.vo;

import com.loan.app.constant.LoanAppConstant;
import com.loan.app.entity.BankDetails;

import javax.persistence.Column;

public class ApplicationRequestVO {
    private BankDetailsRequestVO bankDetailsRequestVO;

    public BankDetailsRequestVO getBankDetailsRequestVO() {
        return bankDetailsRequestVO;
    }

    public void setBankDetailsRequestVO(BankDetailsRequestVO bankDetailsRequestVO) {
        this.bankDetailsRequestVO = bankDetailsRequestVO;
    }
    private int applicationId;

    private int customerId;
    private final String prodCode= LoanAppConstant.PROD_CODE;
    private String createDate;
    private String updateDate;


    public int getApplicationId() { return applicationId; }

    public int getCustomerId() { return customerId; }

    public String getCreateDate() { return createDate; }

    public String getUpdateDate() { return updateDate; }

    public void setApplicationId(int applicationId) { this.applicationId = applicationId; }

    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public void setCreateDate(String createDate) { this.createDate = createDate; }

    public void setUpdateDate(String updateDate) { this.updateDate = updateDate; }
}
