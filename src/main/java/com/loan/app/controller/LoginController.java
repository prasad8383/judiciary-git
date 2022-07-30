package com.loan.app.controller;

import com.loan.app.service.LoginService;
import com.loan.app.vo.UserCredentialRequestVO;
import com.loan.app.vo.UserRegistrationRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/login")
    public String checkUser(@RequestBody UserCredentialRequestVO userCredentialVO){
        return loginService.checkLogin(userCredentialVO);
    }

    @PostMapping(value = "/register")
    public String checkUser(@RequestBody UserRegistrationRequestVO userRegistrationRequstVO){
        return loginService.registerUser(userRegistrationRequstVO);
    }
}
