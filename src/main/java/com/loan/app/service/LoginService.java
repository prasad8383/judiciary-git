package com.loan.app.service;

import com.loan.app.entity.Application;
import com.loan.app.vo.ApplicationRequestVO;
import com.loan.app.vo.UserCredentialRequestVO;
import com.loan.app.vo.UserRegistrationRequestVO;

import java.util.HashMap;
import java.util.List;

public interface LoginService {
    String checkLogin(HashMap<String, String> loginValues);
    void registerUser(HashMap<String, String> userRegData);

    List<Application> getAllApplications();
}
