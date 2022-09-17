package com.loan.app.vo;

import java.util.List;

public class ApplicationAndOfferVO {
    private ApplicationRequestVO applicationRequestVO;
    private List<LoanOfferVO> loanOfferVOS;
    private String firstName;
    private String middleName;
    private String lastName;
    private String mobileNumber;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private int customerId;

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public ApplicationRequestVO getApplicationRequestVO() {
        return applicationRequestVO;
    }

    public void setApplicationRequestVO(ApplicationRequestVO applicationRequestVO) {
        this.applicationRequestVO = applicationRequestVO;
    }

    public List<LoanOfferVO> getLoanOfferVOS() {
        return loanOfferVOS;
    }

    public void setLoanOfferVOS(List<LoanOfferVO> loanOfferVOS) {
        this.loanOfferVOS = loanOfferVOS;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
