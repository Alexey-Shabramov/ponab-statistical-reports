package com.uz.laboratory.statistical.dao.util;


import java.io.IOException;

public interface HibernateUtilDao {
    void dumpDataBase();

    void backupDataBaseFromServer() throws IOException;
}
