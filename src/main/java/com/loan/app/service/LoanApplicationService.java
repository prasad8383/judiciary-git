package com.loan.app.service;

import com.loan.app.entity.Application;
import com.loan.app.vo.ApplicationAndOfferVO;
import com.loan.app.vo.ApplicationRequestVO;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

public interface LoanApplicationService {
    ApplicationRequestVO createLoanApplication(HashMap applicationFormValue);

    ApplicationRequestVO viewLoanApplication(String applicationId);

    ApplicationRequestVO deleteLoanApplication(String applicationId);

    void generateOffer(int applicationId);

    ApplicationAndOfferVO getApplicationAndOfferData(int applicationId);

    ModelAndView getApplicationAndOfferDetails(ApplicationAndOfferVO applicationAndOfferVO);

    Application getApplicationByCustomerId(int customerId);
}
