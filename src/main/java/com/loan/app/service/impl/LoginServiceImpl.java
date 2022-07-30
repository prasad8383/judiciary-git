package com.loan.app.service.impl;

import com.loan.app.constant.LoanAppConstant;
import com.loan.app.dao.LoanAppDAO;
import com.loan.app.entity.Customer;
import com.loan.app.entity.UserCredential;
import com.loan.app.service.LoginService;
import com.loan.app.vo.UserCredentialVO;
import com.loan.app.vo.UserRegistrationRequstVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoanAppDAO loanAppDAO;

    @Override
    public String checkLogin(UserCredentialVO userCredentialVO) {
        if(!ObjectUtils.isEmpty(userCredentialVO)) {
            UserCredential userCredential = loanAppDAO.checkUserExistOrNot(userCredentialVO.getUserId());
            if (!ObjectUtils.isEmpty(userCredential) && LoanAppConstant.USER_ROLE_ADMIN.equalsIgnoreCase(userCredential.getUserRole())){
                //todo bank journey page
                return "adminPage";
            }else if(!ObjectUtils.isEmpty(userCredential) && LoanAppConstant.USER_ROLE_CUSTOMER.equalsIgnoreCase(userCredential.getUserRole())){
                //todo customer journey page
                return "custPage";
            }
        }
        //todo invilid credential
        return "homepage";
    }

    @Override
    public String registerUser(UserRegistrationRequstVO userRegistrationRequstVO) {
        //todo validation for register user fields
        if(!ObjectUtils.isEmpty(userRegistrationRequstVO) && !ObjectUtils.isEmpty(userRegistrationRequstVO.getUserCredentialVO())){
                //todo fname, mname, lname will go to customer
                //todo userId, userPassword, roleId will goto userCredential
            List<Object> entities = new ArrayList<>();

            Customer customer = new Customer();
            UserCredential userCredential = new UserCredential();
            //todo set values accordingly from request vo class

            entities.add(customer);
            entities.add(userCredential);
            loanAppDAO.saveEntities(entities);
        }


        return null;
    }
}
