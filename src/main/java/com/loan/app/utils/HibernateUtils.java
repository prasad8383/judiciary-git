package com.loan.app.utils;

import com.loan.app.config.HibernateConfig;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;

@Configuration
public class HibernateUtils {
    private static Logger logger = LoggerFactory.getLogger(HibernateUtils.class);

    @Autowired
    public SessionFactory sessionFactory;

    public HibernateUtils() {
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        try {
            sessionFactory = HibernateConfig.getSessionFactory();
            return !ObjectUtils.isEmpty(sessionFactory) ? sessionFactory.openSession() : null;
        } catch (Exception var3) {
            logger.error("(:) Error While Getting Session Object (:) :: {}", var3);
        }
        return null;
    }

    public void closeSession(Session session) {
        if (session != null) {
            session.close();
        }

    }
}
