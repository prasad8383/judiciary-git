package com.loan.app.controller;

import com.loan.app.entity.Application;
import com.loan.app.entity.Customer;
import com.loan.app.service.LoanApplicationService;
import com.loan.app.service.LoginService;
import com.loan.app.vo.ApplicationAndOfferVO;
import com.loan.app.vo.ApplicationRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@Controller
public class LoanApplicationController {

    @Autowired
    private LoanApplicationService loanApplicationService;

    @Autowired
    private LoginService loginService;
    @GetMapping("/appPage")
    public ModelAndView index(@RequestParam int userId) {
        Customer customer = loginService.getCustomerByUserId(userId);
        ModelAndView modelAndView = new ModelAndView("customer");
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @PostMapping(value = "/create")
    public ModelAndView createLoanApplication(@RequestParam HashMap applicationFormValue){
       try {
           ApplicationRequestVO applicationRes =  loanApplicationService.createLoanApplication(applicationFormValue);
           ApplicationAndOfferVO applicationAndOfferVO = loanApplicationService.getApplicationAndOfferData(applicationRes.getApplicationId());
           return loanApplicationService.getApplicationAndOfferDetails(applicationAndOfferVO);
       }catch(Exception e){
           throw e;
       }
    }

    @PostMapping(value = "/view")
    public ApplicationRequestVO viewLoanApplication(@RequestBody String applicationId){
        try {
            return loanApplicationService.viewLoanApplication(applicationId);
        }catch(Exception e){
            return null;
        }
    }

    @PostMapping(value = "/delete")
    public ApplicationRequestVO deleteLoanApplication(@RequestBody String applicationId){
        try {
            return loanApplicationService.deleteLoanApplication(applicationId);
        }catch(Exception e){
            return null;
        }
    }
}
