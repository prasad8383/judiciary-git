package com.loan.app.service;

import com.loan.app.vo.UserCredentialVO;
import com.loan.app.vo.UserRegistrationRequstVO;

public interface LoginService {
    String checkLogin(UserCredentialVO userCredentialVO);

    String registerUser(UserRegistrationRequstVO userRegistrationRequstVO);
}
