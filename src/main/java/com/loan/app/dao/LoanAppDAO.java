package com.loan.app.dao;

import com.loan.app.entity.UserCredential;

import java.util.List;

public interface LoanAppDAO {
    UserCredential checkUserExistOrNot(String userId);
    List<Object> saveEntities(List<Object> entities);
}