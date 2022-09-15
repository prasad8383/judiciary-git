package com.loan.app.vo;

public class ApplicationAndOfferVO {
    private ApplicationRequestVO applicationRequestVO;
    private LoanOfferVO loanOfferVO;
    private String firstName;
    private String lastName;
    private String mobileNumber;

    public ApplicationRequestVO getApplicationRequestVO() {
        return applicationRequestVO;
    }

    public void setApplicationRequestVO(ApplicationRequestVO applicationRequestVO) {
        this.applicationRequestVO = applicationRequestVO;
    }

    public LoanOfferVO getLoanOfferVO() {
        return loanOfferVO;
    }

    public void setLoanOfferVO(LoanOfferVO loanOfferVO) {
        this.loanOfferVO = loanOfferVO;
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
