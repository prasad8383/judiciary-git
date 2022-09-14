package com.loan.app.service.impl;

import com.loan.app.dao.LoanAppDAO;
import com.loan.app.entity.Application;
import com.loan.app.entity.BankDetails;
import com.loan.app.service.LoanApplicationService;
import com.loan.app.vo.ApplicationRequestVO;
import com.loan.app.vo.BankDetailsRequestVO;
import org.springframework.beans.factory.annotation.Autowired;

public class LoanApplicationServiceImpl implements LoanApplicationService {
    @Autowired
    private LoanAppDAO loanAppDAO;

    @Override
    public String createLoanApplication(ApplicationRequestVO applicationRequestVO) {
        BankDetailsRequestVO bankDetailsRequestVO = applicationRequestVO.getBankDetailsRequestVO();

        Application application = new Application();
        BankDetails bankDetails = new BankDetails();

        application.setApplicationId(applicationRequestVO.getApplicationId());
        application.setCustomerId(applicationRequestVO.getCustomerId());
        application.setCreateDate(applicationRequestVO.getUpdateDate());

        bankDetails.setAcctNo(bankDetailsRequestVO.getAcctNo());
        bankDetails.setCustomerId(bankDetailsRequestVO.getCustomerId());
        bankDetails.setBankName(bankDetailsRequestVO.getBankName());
        bankDetails.setAnnualIncome(bankDetailsRequestVO.getAnnualIncome());
        bankDetails.setAddress(bankDetailsRequestVO.getAddress());
        bankDetails.setPanNO(bankDetailsRequestVO.getPanNO());
        bankDetails.setPanNO(bankDetailsRequestVO.getPanNO());

        loanAppDAO.saveOrUpdateBankDetails(bankDetails);
        return loanAppDAO.saveApplication(application).toString();
    }

    @Override
    public ApplicationRequestVO viewLoanApplication(String applicationId) {
        Application application = loanAppDAO.getApplicationByAppId(applicationId);
        BankDetails bankDetails = loanAppDAO.getBankDetailsByCustomerId(application.getCustomerId());

        ApplicationRequestVO applicationRequestVO = setApplicationRequestDataToVO(application, bankDetails);
        return applicationRequestVO;
    }


    private ApplicationRequestVO setApplicationRequestDataToVO(Application application, BankDetails bankDetails) {
        ApplicationRequestVO applicationRequestVO = new ApplicationRequestVO();
        BankDetailsRequestVO bankDetailsRequestVO = new BankDetailsRequestVO();

        applicationRequestVO.setApplicationId(application.getApplicationId());
        applicationRequestVO.setUpdateDate(application.getUpdateDate());
        applicationRequestVO.setCreateDate(application.getCreateDate());
        applicationRequestVO.setCustomerId(application.getCustomerId());

        bankDetailsRequestVO.setPinCode(bankDetails.getPinCode());
        bankDetailsRequestVO.setBankName(bankDetails.getBankName());
        bankDetailsRequestVO.setAddress(bankDetails.getAddress());
        bankDetailsRequestVO.setPanNO(bankDetails.getPanNO());
        bankDetailsRequestVO.setAnnualIncome(bankDetails.getAnnualIncome());
        bankDetailsRequestVO.setCustomerId(bankDetails.getCustomerId());
        bankDetailsRequestVO.setAcctNo(bankDetails.getAcctNo());

        applicationRequestVO.setBankDetailsRequestVO(bankDetailsRequestVO);
        return applicationRequestVO;
    }

    //todo future implemention
    @Override
    public ApplicationRequestVO deleteLoanApplication(String applicationId) {
        return null;
    }
}
