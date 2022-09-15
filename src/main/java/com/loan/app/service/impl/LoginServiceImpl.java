package com.loan.app.service.impl;

import com.loan.app.dao.LoanAppDAO;
import com.loan.app.entity.Application;
import com.loan.app.entity.Customer;
import com.loan.app.entity.UserCredential;
import com.loan.app.service.LoginService;
import com.loan.app.vo.ApplicationRequestVO;
import com.loan.app.vo.UserCredentialRequestVO;
import com.loan.app.vo.UserRegistrationRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoanAppDAO loanDAO;

    @Override
    public UserCredentialRequestVO checkLogin(HashMap<String, String> loginValues) {
        UserCredentialRequestVO userCredentialRequestVO = new UserCredentialRequestVO();
        userCredentialRequestVO.setUserId(loginValues.get("userId"));
        userCredentialRequestVO.setUserPassword(loginValues.get("userPassword"));

        UserCredential userCredential = loanDAO.checkUserExistOrNot(userCredentialRequestVO.getUserId(), userCredentialRequestVO.getUserPassword());
        userCredentialRequestVO.setUserRole(userCredential.getUserRole());
        userCredentialRequestVO.setId(userCredential.getId());
        return userCredentialRequestVO;
    }

    @Override
    public void registerUser(HashMap<String, String> userRegData) {

        UserRegistrationRequestVO userRegistrationRequstVO = new UserRegistrationRequestVO();

        userRegistrationRequstVO.setFirstName(userRegData.get("fname"));
        userRegistrationRequstVO.setLname(userRegData.get("lname"));
        userRegistrationRequstVO.setMname(userRegData.get("mname"));
        userRegistrationRequstVO.setMobNumber(userRegData.get("cnno"));
        userRegistrationRequstVO.setEmail(userRegData.get("email"));

        UserCredentialRequestVO userCredentialRequestVO = new UserCredentialRequestVO();
        userCredentialRequestVO.setUserPassword(userRegData.get("userPassword"));
        userCredentialRequestVO.setUserId(userRegData.get("email"));
        userCredentialRequestVO.setUserRole("customer");

        if(!ObjectUtils.isEmpty(userRegistrationRequstVO) && !ObjectUtils.isEmpty(userCredentialRequestVO)){
            List<Object> entities = new ArrayList<>();

            UserCredential userCredential = new UserCredential();
            userCredential.setUserId(userCredentialRequestVO.getUserId());
            userCredential.setUserPassword(userCredentialRequestVO.getUserPassword());
            userCredential.setUserRole(userCredentialRequestVO.getUserRole());

            loanDAO.saveUserCredential(userCredential);
            userCredential = loanDAO.getUserCredentialByUserId(userCredentialRequestVO.getUserId(), userCredential.getUserPassword());

            Customer customer = new Customer();
            customer.setFname(userRegistrationRequstVO.getFirstName());
            customer.setContactNumber(userRegistrationRequstVO.getMobNumber());
            customer.setLanme(userRegistrationRequstVO.getLname());
            customer.setMname(userRegistrationRequstVO.getMname());
            customer.setEmailId(userRegistrationRequstVO.getEmail());
            customer.setUserCredentialId(userCredential.getId());
            loanDAO.saveCustomer(customer);
        }
    }

    @Override
    public List<Application> getAllApplications() {
        return loanDAO.getApplications();
    }

    @Override
    public Customer getCustomerByUserId(int userId) {
        return loanDAO.getCustomerInfoByUserId(userId);
    }
}
