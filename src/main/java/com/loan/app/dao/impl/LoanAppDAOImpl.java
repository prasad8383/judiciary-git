package com.loan.app.dao.impl;

import com.loan.app.dao.GenericBaseDao;
import com.loan.app.dao.LoanAppDAO;
import com.loan.app.entity.UserCredential;
import com.loan.app.utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class LoanAppDAOImpl extends GenericBaseDao implements LoanAppDAO {


    @Override
    public UserCredential checkUserExistOrNot(String userId) {
        //todo db fetching login
        HibernateUtils hibernateUtils = this.getHibernateUtils();
        try{
            return hibernateUtils.findEntityById(UserCredential.class, userId);
        }catch (Exception e){
            //todo add logger and throw exception
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Object> saveEntities(List<Object> entities) {
        try (Session session = getHibernateUtils().getSession()) {
            for (Object entity : entities) {
                session.saveOrUpdate(entity);
            }
            return entities;
        } catch (Exception e) {
            throw new HibernateException("Server might be down. Please try again later.");
        }
    }
}
