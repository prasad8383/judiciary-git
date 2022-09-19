package com.loan.app.controller;

import com.loan.app.service.LoanApplicationService;
import com.loan.app.vo.ApplicationAndOfferVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GeneratOfferController {
    @Autowired
    private LoanApplicationService loanApplicationService;

    @GetMapping("/generateOffer")
    public void generateOffer(@RequestParam int applicationId) {
        loanApplicationService.generateOffer(applicationId);
    }

    @GetMapping("/viewOffer")
    public ModelAndView viewOffer(@RequestParam int applicationId) {
        ApplicationAndOfferVO applicationAndOfferVO = loanApplicationService.getApplicationAndOfferData(applicationId);
        ModelAndView modelAndView = loanApplicationService.getApplicationAndOfferDetails(applicationAndOfferVO);
        modelAndView.addObject("btnVal", "");
        modelAndView.addObject("btnG", "hidden");
        return modelAndView;
    }
}
