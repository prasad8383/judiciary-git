package com.loan.app.controller;

import com.loan.app.entity.UserCredential;
import com.loan.app.service.LoginService;
import com.loan.app.vo.UserCredentialVO;
import com.loan.app.vo.UserRegistrationRequstVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/login")
    public String checkUser(@RequestBody UserCredentialVO userCredentialVO){
        return loginService.checkLogin(userCredentialVO);
    }

    @PostMapping(value = "/register")
    public String checkUser(@RequestBody UserRegistrationRequstVO userRegistrationRequstVO){
        return loginService.registerUser(userRegistrationRequstVO);
    }
}
