package com.loan.app.service.impl;

import com.loan.app.dao.LoanAppDAO;
import com.loan.app.entity.Application;
import com.loan.app.service.LoanApplicationService;
import com.loan.app.vo.ApplicationRequestVO;
import com.loan.app.vo.BankDetailsRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
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
        BankDetailsRequestVO bankDetailsRequestVO = new BankDetailsRequestVO();

        applicationRequestVO.setApplicationId(application.getApplicationId());
        applicationRequestVO.setUpdateDate(new Date());
        applicationRequestVO.setCreateDate(new Date());
        applicationRequestVO.setCustomerId(application.getCustomerId());

        applicationRequestVO.setBankName(application.getBankName());
        applicationRequestVO.setPanNumber(application.getPanNumber());
        applicationRequestVO.setAnnualIncome(application.getAnnualIncome());
        applicationRequestVO.setCustomerId(application.getCustomerId());
        applicationRequestVO.setAccountNumber(application.getAccountNumber());

        applicationRequestVO.setBankDetailsRequestVO(bankDetailsRequestVO);
        return applicationRequestVO;
    }

    //todo future implemention
    @Override
    public ApplicationRequestVO deleteLoanApplication(String applicationId) {
        return null;
    }
}
