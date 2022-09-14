package com.loan.app.service;

import com.loan.app.vo.ApplicationRequestVO;

public interface LoanApplicationService {
    String createLoanApplication(ApplicationRequestVO createApplicationRequestVO);

    ApplicationRequestVO viewLoanApplication(String applicationId);

    ApplicationRequestVO deleteLoanApplication(String applicationId);
}
