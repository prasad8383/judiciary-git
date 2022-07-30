package com.loan.app.vo;

public class UserRegistrationRequstVO {
    private UserCredentialVO userCredentialVO;
    private String mobNumber;
    private String firstName;


    public UserCredentialVO getUserCredentialVO() {
        return userCredentialVO;
    }

    public void setUserCredentialVO(UserCredentialVO userCredentialVO) {
        this.userCredentialVO = userCredentialVO;
    }

    public String getMobNumber() {
        return mobNumber;
    }

    public void setMobNumber(String mobNumber) {
        this.mobNumber = mobNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
