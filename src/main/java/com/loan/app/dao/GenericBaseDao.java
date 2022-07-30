package com.loan.app.dao;

import com.loan.app.utils.HibernateUtils;
import org.springframework.stereotype.Repository;

@Repository
public class GenericBaseDao {
    HibernateUtils hibernateUtils;
    public GenericBaseDao(){}

    public HibernateUtils getHibernateUtils() {
        return hibernateUtils;
    }

    public void setHibernateUtils(HibernateUtils hibernateUtils) {
        this.hibernateUtils = hibernateUtils;
    }
}
