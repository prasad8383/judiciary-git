package com.loan.app.controller;

import com.loan.app.entity.Application;
import com.loan.app.service.LoanApplicationService;
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
    @GetMapping("/test")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @PostMapping(value = "/create")
    public ModelAndView createLoanApplication(@RequestParam HashMap applicationFormValue){
       try {
           ApplicationRequestVO applicationRes =  loanApplicationService.createLoanApplication(applicationFormValue);
           ModelAndView applicationView = new ModelAndView("application");
           applicationView.addObject("applicationId", applicationRes.getApplicationId());
           applicationView.addObject("annualIncome", applicationRes.getAnnualIncome());
           applicationView.addObject("panNumber", applicationRes.getPanNumber());
           applicationView.addObject("customerId", applicationRes.getCustomerId());
           applicationView.addObject("createDate", applicationRes.getCreateDate());
           return applicationView;
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
