package com.uz.laboratory.statistical.service.util.utilImpl;


import com.uz.laboratory.statistical.dao.util.HibernateUtilDao;
import com.uz.laboratory.statistical.service.util.HibernateUtilService;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

public class HibernateUtilServiceImpl implements HibernateUtilService {
    private HibernateUtilDao dao;

    public void setDao(HibernateUtilDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public void shutdownDataBase() {
        try {
            dao.shutdownDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
