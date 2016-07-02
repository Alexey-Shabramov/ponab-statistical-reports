package com.uz.laboratory.statistical.dao.util;


import java.sql.SQLException;

public interface HibernateUtilDao {
    void shutdownDataBase() throws SQLException;
}
