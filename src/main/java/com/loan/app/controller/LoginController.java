package com.loan.app.controller;

import com.loan.app.constant.LoanAppConstant;
import com.loan.app.service.LoginService;
import com.loan.app.vo.UserRegistrationRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@CrossOrigin(origins ="localhost3000")
@Controller
public class LoginController {
    @Autowired(required = true)
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
        modelAndView.addObject("result", "Invalid user name or password");
        return modelAndView;
    }

    @GetMapping(value = "/reg")
    public ModelAndView regpage(){
        ModelAndView modelAndView = new ModelAndView("reg");
        return modelAndView;
    }

    @PostMapping(value = "/adduser")
    public ModelAndView registerUser(@RequestParam HashMap<String, String> userRegData){
        ModelAndView modelAndView = new ModelAndView("index");
        try {
            loginService.registerUser(userRegData);
        }catch(Exception e){
            //todo add logger
            modelAndView.addObject("result","something went wrong try again");
            return modelAndView;
        }
        modelAndView.addObject("result","Registered successfully please login with your credentials");
        return modelAndView;
    }

}
