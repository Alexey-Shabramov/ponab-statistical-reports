package com.uz.laboratory.statistical.dao.util.utilImpl;


import com.uz.laboratory.statistical.dao.AbstractDao;
import com.uz.laboratory.statistical.dao.util.HibernateUtilDao;
import com.uz.laboratory.statistical.dict.Constants;
import com.uz.laboratory.statistical.util.fx.AlertGuiUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HibernateUtilDaoImpl extends AbstractDao implements HibernateUtilDao {
    public void dumpDataBase() {
        getSession().createSQLQuery("SCRIPT '/database/database.sql'")
                .executeUpdate();
    }

    public void backupDataBaseFromServer() throws IOException {
        if (Files.exists(Paths.get("/database/database.sql"))) {
            getSession().createSQLQuery(new String(Files.readAllBytes(Paths.get("/database/database.sql"))))
                    .executeUpdate();
        } else {
            AlertGuiUtil.createAlert(Constants.DATABASE_SCRIPT_DUMP_NULL);
        }
    }
}
