package com.loan.app.service.impl;

import com.loan.app.constant.LoanAppConstant;
import com.loan.app.dao.LoanAppDAO;
import com.loan.app.entity.Customer;
import com.loan.app.entity.UserCredential;
import com.loan.app.service.LoginService;
import com.loan.app.vo.UserCredentialRequestVO;
import com.loan.app.vo.UserRegistrationRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoanAppDAO loanAppDAO;

    @Override
    public String checkLogin(UserCredentialRequestVO userCredentialVO) {
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
            userCredential.setCustomer(customer);
            userCredential.setUserId(userCredentialRequestVO.getUserId());
            userCredential.setUserPassword(userCredentialRequestVO.getUserPassword());
            userCredential.setUserRole(userCredentialRequestVO.getUserRole());

            entities.add(customer);
            entities.add(userCredential);
            loanAppDAO.saveEntities(entities);
        }
        return null;
    }
}
