package com.loan.app.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Properties;

public class HibernateConfig {
    @Autowired
    private static LoanAppDBApplicationProperties applicationProperties;

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();

                settings.put(Environment.DRIVER, applicationProperties.getDriverName());
                settings.put(Environment.URL, applicationProperties.getUrl());
                settings.put(Environment.USER, applicationProperties.getUserName());
                settings.put(Environment.PASS, applicationProperties.getPassword());
                settings.put(Environment.DIALECT, applicationProperties.getDialect());
                settings.put(Environment.SHOW_SQL, applicationProperties.getShowDDL());
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, applicationProperties.getHbm2ddlAuto());
                configuration.setProperties(settings);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}