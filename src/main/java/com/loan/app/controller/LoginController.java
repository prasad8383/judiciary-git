package com.loan.app.controller;

import com.loan.app.constant.LoanAppConstant;
import com.loan.app.entity.Application;
import com.loan.app.entity.Customer;
import com.loan.app.service.LoanApplicationService;
import com.loan.app.service.LoginService;
import com.loan.app.vo.ApplicationAndOfferVO;
import com.loan.app.vo.UserCredentialRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@Controller
public class LoginController {
    @Autowired(required = true)
    private LoginService loginService;

    @Autowired
    private LoanApplicationService loanApplicationService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView checkUser(@RequestParam HashMap<String, String> loginValues) {
        UserCredentialRequestVO userCredential = loginService.checkLogin(loginValues);
        return viewAccordingToUserRole(userCredential);
    }

    @GetMapping(value = "/reg")
    public ModelAndView regpage() {
        ModelAndView modelAndView = new ModelAndView("reg");
        return modelAndView;
    }

    @PostMapping(value = "/adduser")
    public ModelAndView addUser(@RequestParam HashMap<String, String> userRegData) {
        ModelAndView modelAndView = new ModelAndView("index");
        try {
            loginService.registerUser(userRegData);
        } catch (Exception e) {
            //todo add logger
            modelAndView.addObject("result", "something went wrong try again");
            return modelAndView;
        }
        modelAndView.addObject("result", "Registered successfully please login with your credentials");
        return modelAndView;
    }

    private ModelAndView viewAccordingToUserRole(UserCredentialRequestVO userCredential) {
        ModelAndView modelAndView = new ModelAndView();
        String viewName = "index";

        if (!ObjectUtils.isEmpty(userCredential)) {
            if (LoanAppConstant.USER_ROLE_ADMIN.equalsIgnoreCase(userCredential.getUserRole())) {
                return loanApplicationService.getAllApplications();
            } else if (LoanAppConstant.USER_ROLE_CUSTOMER.equalsIgnoreCase(userCredential.getUserRole())) {
                Customer customer = loginService.getCustomerByUserId(userCredential.getId());
                Application application = loanApplicationService.getApplicationByCustomerId(customer.getCustomerId());
                if (application != null) {
                    ApplicationAndOfferVO applicationAndOfferVO = loanApplicationService.getApplicationAndOfferData(application.getApplicationId());
                    modelAndView = loanApplicationService.getApplicationAndOfferDetails(applicationAndOfferVO);
                } else {
                    //if no application is created then fetch customer data only
                    modelAndView = loanApplicationService.getCustomerInfo(customer);
                }
                modelAndView.addObject("customer", customer);
                return modelAndView;
            }
        }
        modelAndView.setViewName(viewName);
        modelAndView.addObject("result", "Invalid user name or password");
        return modelAndView;
    }
}
