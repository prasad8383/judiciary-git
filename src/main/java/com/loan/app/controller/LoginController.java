package com.loan.app.controller;

import com.loan.app.constant.LoanAppConstant;
import com.loan.app.service.LoginService;
import com.loan.app.vo.UserRegistrationRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(){
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView checkUser(@RequestParam HashMap<String, String> loginValues){

        String userRole = loginService.checkLogin(loginValues);
        ModelAndView modelAndView = new ModelAndView();
        String viewName = "index";

        if (!ObjectUtils.isEmpty(userRole)) {
            if (LoanAppConstant.USER_ROLE_ADMIN.equalsIgnoreCase(userRole)) {
                viewName = "admin";
            } else if (LoanAppConstant.USER_ROLE_CUSTOMER.equalsIgnoreCase(userRole)) {
                viewName = "customer";
            }
            modelAndView.setViewName(viewName);
            return modelAndView;
        }
        modelAndView.setViewName(viewName);
        modelAndView.addObject("Error", "Invalid user name or password");
        return modelAndView;
    }

    @PostMapping(value = "/register")
    public String checkUser(@RequestBody UserRegistrationRequestVO userRegistrationRequstVO){
        //return loginService.registerUser(userRegistrationRequstVO);
        return null;
    }
}
