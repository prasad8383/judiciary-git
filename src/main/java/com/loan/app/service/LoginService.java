package com.loan.app.service;

import com.loan.app.vo.UserCredentialRequestVO;
import com.loan.app.vo.UserRegistrationRequestVO;

public interface LoginService {
    String checkLogin(UserCredentialRequestVO userCredentialVO);

    String registerUser(UserRegistrationRequestVO userRegistrationRequstVO);
}
