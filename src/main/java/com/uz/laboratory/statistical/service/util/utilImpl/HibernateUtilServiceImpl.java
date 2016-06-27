package com.uz.laboratory.statistical.service.util.utilImpl;


import com.uz.laboratory.statistical.dao.util.HibernateUtilDao;
import com.uz.laboratory.statistical.service.util.HibernateUtilService;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

public class HibernateUtilServiceImpl implements HibernateUtilService {
    private HibernateUtilDao dao;

    public void setDao(HibernateUtilDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public void dumpDataBase() {
        dao.dumpDataBase();
    }

    @Override
    @Transactional
    public void backupDataBaseFromServer() throws IOException {
        dao.backupDataBaseFromServer();
    }
}
