package com.loan.app.dao;

import com.loan.app.entity.Application;
import com.loan.app.entity.Customer;
import com.loan.app.entity.UserCredential;

import java.io.Serializable;
import java.util.List;

public interface LoanAppDAO {
    UserCredential checkUserExistOrNot(String userId, String userPassword);
    List<Object> saveEntities(List<Object> entities);
    UserCredential getUserCredentialByUserId(String userId, String userPassword);
    void saveUserCredential(UserCredential userCredential);
    void saveCustomer(Customer customer);

    Serializable saveApplication(Application application);

    /*void saveOrUpdateBankDetails(BankInfo bankDetails);
*/
    Application getApplicationByAppId(int applicationId);

    List<Application> getApplications();



    /* BankInfo getBankDetailsByCustomerId(int customerId);*/


}
