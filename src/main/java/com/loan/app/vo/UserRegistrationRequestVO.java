package com.loan.app.vo;

public class UserRegistrationRequestVO {
    private UserCredentialRequestVO userCredentialVO;
    private String mobNumber;
    private String firstName;
    private String lname;
    private String mname;

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public UserCredentialRequestVO getUserCredentialVO() {
        return userCredentialVO;
    }

    public void setUserCredentialVO(UserCredentialRequestVO userCredentialVO) {
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
