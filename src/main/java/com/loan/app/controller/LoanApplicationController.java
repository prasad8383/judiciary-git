package com.loan.app.controller;

import com.loan.app.service.LoanApplicationService;
import com.loan.app.vo.ApplicationRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanApplicationController {

    @Autowired
    private LoanApplicationService loanApplicationService;
    @GetMapping("/test")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @PostMapping(value = "/create")
    public String createLoanApplication(@RequestBody ApplicationRequestVO applicationRequestVO){
       try {
           return loanApplicationService.createLoanApplication(applicationRequestVO);
       }catch(Exception e){
           return "Error occured while creating loan application: "+e;
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
