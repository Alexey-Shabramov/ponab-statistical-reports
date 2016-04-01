package com.uz.laboratory.statistical.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class AbstractDao {

    private SessionFactory sessionFactory;

    public Session getSession() {
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

