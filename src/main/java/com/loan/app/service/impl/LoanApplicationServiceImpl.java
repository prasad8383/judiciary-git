package com.loan.app.service.impl;

import com.loan.app.dao.LoanAppDAO;
import com.loan.app.entity.Application;
import com.loan.app.entity.Customer;
import com.loan.app.entity.LoanOffer;
import com.loan.app.service.LoanApplicationService;
import com.loan.app.vo.ApplicationAndOfferVO;
import com.loan.app.vo.ApplicationRequestVO;
import com.loan.app.vo.LoanCalculations;
import com.loan.app.vo.LoanOfferVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {
    @Autowired
    private LoanAppDAO loanAppDAO;

    @Override
    public ApplicationRequestVO createLoanApplication(HashMap applicationFormValue) {
        ApplicationRequestVO applicationRequestVO = getApplicationRequestData(applicationFormValue);
        Application application = new Application();

        application.setApplicationId(applicationRequestVO.getApplicationId());
        application.setCustomerId(applicationRequestVO.getCustomerId());
        application.setCreateDate(applicationRequestVO.getCreateDate());
        application.setUpdateDate(applicationRequestVO.getUpdateDate());

        application.setAccountNumber(applicationRequestVO.getAccountNumber());
        application.setCustomerId(applicationRequestVO.getCustomerId());
        application.setBankName(applicationRequestVO.getBankName());

        application.setAnnualIncome(applicationRequestVO.getAnnualIncome());
        application.setRequestedLoanAmt(applicationRequestVO.getRequestedLoanAmt());
        application.setPanNumber(String.valueOf(applicationRequestVO.getPanNumber()));
        int applicationId = Integer.valueOf(loanAppDAO.saveApplication(application).toString());
        //todo add not null condition on application id
        application = loanAppDAO.getApplicationByAppId(applicationId);
        applicationRequestVO.setApplicationId(application.getApplicationId());
        return applicationRequestVO;
    }

    private ApplicationRequestVO getApplicationRequestData(HashMap applicationFormValue) {
        ApplicationRequestVO applicationRequestVO = new ApplicationRequestVO();

        applicationRequestVO.setAccountNumber(Integer.valueOf((String) applicationFormValue.get("accNo")));
        applicationRequestVO.setBankName((String) applicationFormValue.get("bankName"));
        applicationRequestVO.setCustomerId(Integer.valueOf((String)applicationFormValue.get("customerId")));
        applicationRequestVO.setAnnualIncome(Integer.valueOf((String)applicationFormValue.get("annualIncome")));
        applicationRequestVO.setRequestedLoanAmt(Integer.valueOf((String)applicationFormValue.get("requestedLoanAmt")));
        applicationRequestVO.setPanNumber((String)applicationFormValue.get("panNo"));
        applicationRequestVO.setCustomerId(Integer.valueOf((String)applicationFormValue.get("customerId")));
        applicationRequestVO.setUpdateDate(new Date());
        applicationRequestVO.setCreateDate(new Date());
        return applicationRequestVO;
    }

    @Override
    public ApplicationRequestVO viewLoanApplication(String applicationId) {
        Application application = loanAppDAO.getApplicationByAppId(Integer.parseInt(applicationId));
        ApplicationRequestVO applicationRequestVO = setApplicationRequestDataToVO(application);
        return applicationRequestVO;
    }


    private ApplicationRequestVO setApplicationRequestDataToVO(Application application) {
        ApplicationRequestVO applicationRequestVO = new ApplicationRequestVO();

        applicationRequestVO.setApplicationId(application.getApplicationId());
        applicationRequestVO.setUpdateDate(new Date());
        applicationRequestVO.setCreateDate(new Date());
        applicationRequestVO.setCustomerId(application.getCustomerId());

        applicationRequestVO.setBankName(application.getBankName());
        applicationRequestVO.setPanNumber(application.getPanNumber());
        applicationRequestVO.setAnnualIncome(application.getAnnualIncome());
        applicationRequestVO.setCustomerId(application.getCustomerId());
        applicationRequestVO.setAccountNumber(application.getAccountNumber());

        return applicationRequestVO;
    }

    //todo future implemention
    @Override
    public ApplicationRequestVO deleteLoanApplication(String applicationId) {
        return null;
    }

    @Override
    public void generateOffer(int applicationId) {
        Application application = loanAppDAO.getApplicationByAppId(applicationId);
        int score = checkCustCibilByPan(application.getPanNumber());

        LoanOffer loanOffer = new LoanOffer();
        loanOffer.setApplicationId(applicationId);
        loanOffer.setLoanType(application.getProdCode());
        loanOffer.setLoanCreateDate(new Date());
        loanOffer.setTenure(2);//todo need to change it according to cibil score

        LoanCalculations loanCalculations = calculateInterestAmount(score, application.getRequestedLoanAmt());
        loanOffer.setLoanAmount(loanCalculations.getSacLoanAmount());
        loanOffer.setInterestAmount(loanCalculations.getCalcInterestAmount());
        loanOffer.setInterestRate(loanCalculations.getIntersetRate());
        loanOffer.setStatus("Offered");
        loanAppDAO.saveLoanOffer(loanOffer);
    }

    @Override
    public ApplicationAndOfferVO getApplicationAndOfferData(int applicationId) {
        ApplicationAndOfferVO applicationAndOfferVO = new ApplicationAndOfferVO();
        ApplicationRequestVO applicationRequestVO = new ApplicationRequestVO();
        LoanOfferVO loanOfferVO = new LoanOfferVO();

        Application application = loanAppDAO.getApplicationByAppId(applicationId);
        LoanOffer loanOffer = loanAppDAO.getLoanOfferByApplicationId(applicationId);
        Customer customer = loanAppDAO.getCustomerInfoByCustomerId(application.getCustomerId());

        //set application values
        applicationRequestVO.setApplicationId(application.getApplicationId());
        applicationRequestVO.setPanNumber(application.getPanNumber());
        applicationRequestVO.setAccountNumber(application.getAccountNumber());
        applicationRequestVO.setAnnualIncome(application.getAnnualIncome());
        applicationRequestVO.setBankName(application.getBankName());
        applicationRequestVO.setCreateDate(application.getCreateDate());

        //set offers value
        loanOfferVO.setLoanAmount(loanOffer.getLoanAmount());
        loanOfferVO.setLoanType(loanOffer.getLoanType());
        loanOfferVO.setTenure(loanOffer.getTenure());
        loanOfferVO.setInterestAmount(loanOffer.getInterestAmount());
        loanOfferVO.setInterestRate(loanOffer.getInterestRate());
        loanOfferVO.setStatus(loanOffer.getStatus());

        //set customer value
        applicationAndOfferVO.setFirstName(customer.getFname());
        applicationAndOfferVO.setLastName(customer.getLanme());
        applicationAndOfferVO.setMobileNumber(customer.getContactNumber());

        applicationAndOfferVO.setApplicationRequestVO(applicationRequestVO);
        applicationAndOfferVO.setLoanOfferVO(loanOfferVO);
        return applicationAndOfferVO;
    }

    private LoanCalculations calculateInterestAmount(int score, int loanAmount) {
        LoanCalculations loanCalculations = new LoanCalculations();

        if(score < 650){
            loanAmount = loanAmount * 80/100;
            loanCalculations.setIntersetRate(15);
        }else{
            loanAmount = loanAmount * 90/100;
            loanCalculations.setIntersetRate(8);
        }
        loanCalculations.setSacLoanAmount(loanAmount);
        loanAmount = loanAmount * loanCalculations.getIntersetRate() /100;
        loanCalculations.setCalcInterestAmount(loanAmount);
        return loanCalculations;
    }

    private int checkCustCibilByPan(String panNumber) {

        return 700;
    }
}
