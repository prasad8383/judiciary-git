package com.loan.app.service;

import com.loan.app.vo.UserCredentialRequestVO;
import com.loan.app.vo.UserRegistrationRequestVO;

import java.util.HashMap;

public interface LoginService {
    String checkLogin(HashMap<String, String> loginValues);
    void registerUser(HashMap<String, String> userRegData);
}
