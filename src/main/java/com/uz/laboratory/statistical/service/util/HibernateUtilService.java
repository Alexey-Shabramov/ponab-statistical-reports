package com.uz.laboratory.statistical.service.util;


import java.io.IOException;

public interface HibernateUtilService {
    void dumpDataBase();

    void backupDataBaseFromServer() throws IOException;
}
