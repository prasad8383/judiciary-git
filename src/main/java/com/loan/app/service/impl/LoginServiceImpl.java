package com.loan.app.service.impl;

import com.loan.app.dao.LoanAppDAO;
import com.loan.app.entity.Customer;
import com.loan.app.entity.UserCredential;
import com.loan.app.service.LoginService;
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
    public String checkLogin(HashMap<String, String> loginValues) {
        UserCredentialRequestVO userCredentialRequestVO = new UserCredentialRequestVO();
        userCredentialRequestVO.setUserId(loginValues.get("userId"));
        userCredentialRequestVO.setUserPassword(loginValues.get("userPassword"));

        UserCredential userCredential = loanDAO.checkUserExistOrNot(userCredentialRequestVO.getUserId());
        return !ObjectUtils.isEmpty(userCredential)?userCredential.getUserRole():null;
    }

    @Override
    public String registerUser(UserRegistrationRequestVO userRegistrationRequstVO) {
        if(!ObjectUtils.isEmpty(userRegistrationRequstVO) && !ObjectUtils.isEmpty(userRegistrationRequstVO.getUserCredentialVO())){
            UserCredentialRequestVO userCredentialRequestVO = userRegistrationRequstVO.getUserCredentialVO();

            List<Object> entities = new ArrayList<>();

            Customer customer = new Customer();
            customer.setFname(userRegistrationRequstVO.getFirstName());
            customer.setContactNumber(userRegistrationRequstVO.getMobNumber());
            customer.setLanme(userRegistrationRequstVO.getLname());
            customer.setMname(userRegistrationRequstVO.getMname());

            UserCredential userCredential = new UserCredential();
            //userCredential.setCustomer(customer);
            userCredential.setUserId(userCredentialRequestVO.getUserId());
            userCredential.setUserPassword(userCredentialRequestVO.getUserPassword());
            userCredential.setUserRole(userCredentialRequestVO.getUserRole());

            entities.add(customer);
            entities.add(userCredential);
            loanDAO.saveEntities(entities);
        }
        return null;
    }
}
