package com.loan.app.dao.impl;

import com.loan.app.dao.LoanAppDAO;
import com.loan.app.entity.Application;
import com.loan.app.entity.Customer;
import com.loan.app.entity.LoanOffer;
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
        try {
            userCredential = getUserCredentialByUserId(userId, userPassword);
        } catch (Exception e) {
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
        try (Session session = hibernateUtils.getSession()) {
            session.saveOrUpdate(userCredential);
        } catch (Exception e) {
            throw new HibernateException("Server might be down. Please try again later.");
        }
    }

    @Override
    public void saveCustomer(Customer customer) {
        try (Session session = hibernateUtils.getSession()) {
            session.saveOrUpdate(customer);
        } catch (Exception e) {
            throw new HibernateException("Server might be down. Please try again later.");
        }
    }

    @Override
    public String saveApplication(Application application) {
        try (Session session = hibernateUtils.getSession()) {
            return session.save(application).toString();
        } catch (Exception e) {
            throw new HibernateException("Server might be down. Please try again later.");
        }
    }

    @Override
    public Application getApplicationByAppId(int applicationId) {
        Session session = hibernateUtils.getSession();
        return session.get(Application.class, applicationId);
    }

    @Override
    public List<Application> getApplications() {
        String hql = "from Application";
        Session session = hibernateUtils.getSession();
        Query query = session.createQuery(hql);
        return query.getResultList();
    }

    @Override
    public void saveLoanOffer(LoanOffer loanOffer) {
        try (Session session = hibernateUtils.getSession()) {
            session.save(loanOffer);
        } catch (Exception e) {
            throw new HibernateException("Server might be down. Please try again later.");
        }
    }

    @Override
    public List<LoanOffer> getLoanOfferByApplicationId(int applicationId) {
        String hql = "from LoanOffer lo where lo.applicationId = :applicationId";
        Session session = hibernateUtils.getSession();
        Query query = session.createQuery(hql);
        query.setParameter("applicationId", applicationId);
        return (List<LoanOffer>) (!query.getResultList().isEmpty() ? query.getResultList() : null);
    }

    @Override
    public Customer getCustomerInfoByCustomerId(int customerId) {
        Session session = hibernateUtils.getSession();
        return session.get(Customer.class, customerId);
    }

    @Override
    public Customer getCustomerInfoByUserId(int userCredentialId) {
        String hql = "from Customer cust where cust.userCredentialId = :userCredentialId";
        Session session = hibernateUtils.getSession();
        Query query = session.createQuery(hql);
        query.setParameter("userCredentialId", userCredentialId);
        return (Customer) query.getResultList().get(0);
    }

    @Override
    public Application getApplicationByCustomerId(int customerId) {
        String hql = "from Application app where app.customerId = :customerId";
        Session session = hibernateUtils.getSession();
        Query query = session.createQuery(hql);
        query.setParameter("customerId", customerId);
        return (Application) (!query.getResultList().isEmpty() ? query.getResultList().get(0) : null);
    }

}
