package com.loan.app.dao.impl;

import com.loan.app.dao.LoanAppDAO;
import com.loan.app.entity.UserCredential;
import com.loan.app.utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoanAppDAOImpl implements LoanAppDAO {
    private static Logger logger = LoggerFactory.getLogger(LoanAppDAOImpl.class);
    @Autowired
    HibernateUtils hibernateUtils;
    @Override
    public UserCredential checkUserExistOrNot(String userId) {
        UserCredential userCredential = null;
        Session session = hibernateUtils.getSession();
        try{
            userCredential = session.find(UserCredential.class, userId);
        }catch (Exception e){
            logger.error("LoanAppDAOImpl::checkUserExistOrNot() failed while fetching data from DB: {}", e);
        }finally {
            session.close();
        }
        return userCredential;
    }

    @Override
    public List<Object> saveEntities(List<Object> entities) {
        try (Session session = hibernateUtils.getSession()) {
            for (Object entity : entities) {
                session.saveOrUpdate(entity);
            }
            return entities;
        } catch (Exception e) {
            throw new HibernateException("Server might be down. Please try again later.");
        }
    }
}
