package com.loan.app.utils;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

@Configuration
public class HibernateUtils {
    private static Logger logger = LoggerFactory.getLogger(HibernateUtils.class);
    @Autowired
    public SessionFactory sessionFactory;
    @Autowired
    @Qualifier("HibernateInterceptorConfig")
    public Object hibernateInterceptor;
    @Value("${ENV_PASS}")
    public String databaseType;

    public HibernateUtils() {
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() throws Exception {
        try {
            SessionFactory sessionFact = this.getSessionFactory();
            if (sessionFact != null) {
                Session session = null;
                if (this.hibernateInterceptor != null) {
                    session = sessionFact.withOptions().interceptor((Interceptor)this.hibernateInterceptor).openSession();
                } else {
                    session = sessionFact.openSession();
                }

                return session;
            } else {
                logger.error("There is an error While creating Bean with SessionFactory");
                throw new HibernateException("Unable to acquire database session, SessionFactory empty");
            }
        } catch (Exception var3) {
            logger.error("(:) Error While Getting Session Object (:) :: {}", var3);
            throw var3;
        }
    }

    public void closeSession(Session session) {
        if (session != null) {
            session.close();
        }

    }

    public <T> T saveEntity(T entity) throws Throwable {
        Transaction tx = null;

        try {
            Session session = this.getSession();
            Throwable var4 = null;

            Object var5;
            try {
                tx = session.beginTransaction();
                session.save(entity);
                tx.commit();
                var5 = entity;
            } catch (Throwable var15) {
                var4 = var15;
                throw var15;
            } finally {
                if (session != null) {
                    if (var4 != null) {
                        try {
                            session.close();
                        } catch (Throwable var14) {
                        }
                    } else {
                        session.close();
                    }
                }

            }
        } catch (Exception var17) {
            logger.error("(:) Error in SaveEntity Method (:) {}", var17.getMessage());
            if (tx != null) {
                tx.rollback();
            }

            throw var17;
        }
        return null;
    }

    public <T> T saveOrUpdateEntity(T entity, Session session) throws Exception {
        logger.debug("saveOrUpdateEntity() : Entity Class ->> :: {} ", entity.getClass());
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(entity);
            tx.commit();
            return entity;
        } catch (Exception var5) {
            logger.error("(:) Error in Entity session SaveOrUpdateEntity Method (:) ");
            if (tx != null) {
                tx.rollback();
            }
        }
        return null;
    }

    public <T> T deleteEntity(T entity, Serializable primaryId) throws Throwable {
        Transaction tx = null;
        try {
            Session session = this.getSession();
            Throwable var5 = null;

            Object var7;
            try {
                tx = session.beginTransaction();
                Object dataObject = session.get(entity.getClass(), primaryId);
                session.delete(dataObject);
                tx.commit();
                var7 = entity;
            } catch (Throwable var17) {
                var5 = var17;
                throw var17;
            } finally {
                if (session != null) {
                    if (var5 != null) {
                        try {
                            session.close();
                        } catch (Throwable var16) {
                        }
                    } else {
                        session.close();
                    }
                }

            }

            return null;
        } catch (Exception var19) {
            logger.error("Error in DeleteEntity with entity:{}, primaryId: {}, error: {}", new Object[]{entity.getClass().getName(), primaryId, var19.getMessage()});
            if (tx != null) {
                tx.rollback();
            }

            throw var19;
        }
    }

    public <T> T findEntityById(Class<T> entityClass, Serializable primaryId) throws Throwable {
        Session session = this.getSession();
        Throwable var4 = null;

        Object var6;
        try {
            Object dataObject = session.get(entityClass, primaryId);
            var6 = dataObject;
        } catch (Throwable var15) {
            var4 = var15;
            throw var15;
        } finally {
            if (session != null) {
                if (var4 != null) {
                    try {
                        session.close();
                    } catch (Throwable var14) {
                    }
                } else {
                    session.close();
                }
            }

        }
        return null;
    }

}
