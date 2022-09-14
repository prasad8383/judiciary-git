package com.loan.app.dao.impl;

import com.loan.app.dao.LoanAppDAO;
import com.loan.app.entity.Application;
import com.loan.app.entity.BankDetails;
import com.loan.app.entity.Customer;
import com.loan.app.entity.UserCredential;
import com.loan.app.utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class LoanAppDAOImpl implements LoanAppDAO {
    private static Logger logger = LoggerFactory.getLogger(LoanAppDAOImpl.class);
    @Autowired
    HibernateUtils hibernateUtils;
    @Override
    public UserCredential checkUserExistOrNot(String userId, String userPassword) {
        UserCredential userCredential = null;
        try{
            userCredential = getUserCredentialByUserId(userId, userPassword);
        }catch (Exception e){
            logger.error("LoanAppDAOImpl::checkUserExistOrNot() failed while fetching data from DB: {}", e);
        }
        return userCredential;
    }

    public UserCredential getUserCredentialByUserId(String userId, String userPassword) {
        String hql = "from UserCredential uc where uc.userId = :userId and userPassword = :userPassword";
        Session session = hibernateUtils.getSession();
        Query query = session.createQuery(hql);
        query.setParameter("userId", userId);
        query.setParameter("userPassword", userPassword);
        return (UserCredential) query.getResultList().get(0);
    }

    @Override
    public List<Object> saveEntities(List<Object> entities) {
        try (Session session = hibernateUtils.getSession()) {
            for (Object entity : entities) {
                session.saveOrUpdate(entity);
                session.close();
            }
            return entities;
        } catch (Exception e) {
            throw new HibernateException("Server might be down. Please try again later.");
        }
    }

    @Override
    public void saveUserCredential(UserCredential userCredential) {
        try(Session session = hibernateUtils.getSession()){
            session.saveOrUpdate(userCredential);
        }catch (Exception e){
            throw new HibernateException("Server might be down. Please try again later.");
        }
    }

    @Override
    public void saveCustomer(Customer customer) {
        try(Session session = hibernateUtils.getSession()){
            session.saveOrUpdate(customer);
        }catch (Exception e){
            throw new HibernateException("Server might be down. Please try again later.");
        }
    }

    @Override
    public String saveApplication(Application application) {
        try(Session session = hibernateUtils.getSession()){
            return session.save(application).toString();
        }catch (Exception e){
            throw new HibernateException("Server might be down. Please try again later.");
        }
    }

    @Override
    public void saveOrUpdateBankDetails(BankDetails bankDetails) {
        try(Session session = hibernateUtils.getSession()){
            session.saveOrUpdate(bankDetails);
        }catch (Exception e){
            throw new HibernateException("Server might be down. Please try again later.");
        }
    }

    @Override
    public Application getApplicationByAppId(String applicationId) {
        Session session = hibernateUtils.getSession();
        return session.get(Application.class, applicationId);
    }

    @Override
    public BankDetails getBankDetailsByCustomerId(int customerId) {
        String hql = "from BankDetails bnk where bnk.customerId = :customerId";
        Session session = hibernateUtils.getSession();
        Query query = session.createQuery(hql);
        query.setParameter("customerId", customerId);
        return (BankDetails) query.getResultList().get(0);
    }
}
