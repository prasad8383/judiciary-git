package com.loan.app.controller;

import com.loan.app.service.LoanApplicationService;
import com.loan.app.vo.ApplicationAndOfferVO;
import com.loan.app.vo.ApplicationRequestVO;
import com.loan.app.vo.LoanOfferVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GeneratOfferController {
    @Autowired
    private LoanApplicationService loanApplicationService;

    @GetMapping("/testajax")
    public void testAjax(@RequestParam int applicationId){
        loanApplicationService.generateOffer(applicationId);
    }
    @GetMapping("/viewOffer")
    public ModelAndView viewOffer(@RequestParam int applicationId){
        ApplicationAndOfferVO applicationAndOfferVO = loanApplicationService.getApplicationAndOfferData(applicationId);
        ApplicationRequestVO applicationRequestVO = applicationAndOfferVO.getApplicationRequestVO();
        LoanOfferVO loanOfferVO = applicationAndOfferVO.getLoanOfferVO();

        ModelAndView modelAndView = new ModelAndView("offer");
        modelAndView.addObject("firstName",applicationAndOfferVO.getFirstName() );
        modelAndView.addObject("lastName", applicationAndOfferVO.getLastName());
        modelAndView.addObject("pan", applicationRequestVO.getPanNumber());
        modelAndView.addObject("mobileNumber",applicationAndOfferVO.getMobileNumber());
        modelAndView.addObject("applicationNumber",applicationRequestVO.getApplicationId() );
        modelAndView.addObject("bankName", applicationRequestVO.getBankName());
        modelAndView.addObject("accountNumber", applicationRequestVO.getAccountNumber() );
        modelAndView.addObject("annualIncome", applicationRequestVO.getAnnualIncome());
        modelAndView.addObject("createdDate", applicationRequestVO.getCreateDate() );
        modelAndView.addObject("loanType", loanOfferVO.getLoanType());
        modelAndView.addObject("loanAmount", loanOfferVO.getLoanAmount());
        modelAndView.addObject("interestRate", loanOfferVO.getInterestRate());
        modelAndView.addObject("interestAmount", loanOfferVO.getInterestAmount());
        modelAndView.addObject("tenure", loanOfferVO.getTenure());
        modelAndView.addObject("totalLoanAmount", (loanOfferVO.getInterestAmount() + loanOfferVO.getLoanAmount()));
        modelAndView.addObject("offerStatus", loanOfferVO.getStatus());
        modelAndView.addObject("loanCreatedDate", loanOfferVO.getLoanCreateDate());
        return modelAndView;
    }
}
