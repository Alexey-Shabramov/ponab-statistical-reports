package com.uz.laboratory.statistical.dao.util.utilImpl;


import com.uz.laboratory.statistical.dao.AbstractDao;
import com.uz.laboratory.statistical.dao.util.HibernateUtilDao;
import com.uz.laboratory.statistical.dict.Constants;
import com.uz.laboratory.statistical.util.fx.AlertGuiUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class HibernateUtilDaoImpl extends AbstractDao implements HibernateUtilDao {

    public void shudownDataBase() throws SQLException {
        getSession().createSQLQuery("SHUTDOWN").executeUpdate();
    }

    public void backupDataBaseFromServer() throws IOException {
    }

    public void test() throws IOException {
        /**
         *
         */
        if (Files.exists(Paths.get("/database/ponab_statistical_reports.log"))) {
            String mainScript = new String(Files.readAllBytes(Paths.get("/database/ponab_statistical_reports.log")));
            mainScript = mainScript.substring(mainScript.indexOf("INSERT"));
            mainScript = StringUtils.replaceEach(mainScript,
                    new String[]{"INSERT", "DELETE", "=======", "<<<<<<< HEAD", ">>>>>>> branch 'master' of https://github.com/Alexey-Shabramov/ponab-statistical-hsqldb-base.git\n" +
                            "COMMIT", ":", "\\n"},
                    new String[]{" INSERT", " DELETE", "", "", "", "\\:", ""}
            );

            List<String> queryList = new LinkedList<>(Arrays.asList(mainScript.split("COMMIT")));
            queryList.remove(0);
            queryList.forEach(item -> System.out.print(queryList.indexOf(item) + item));
            getSessionFactory().openSession().createSQLQuery("TRUNCATE SCHEMA PUBLIC RESTART IDENTITY AND COMMIT NO CHECK").executeUpdate();
            queryList.forEach(query -> getSession().createSQLQuery(query).executeUpdate());
        } else {
            AlertGuiUtil.createAlert(Constants.DATABASE_SCRIPT_DUMP_NULL);
        }
    }
}
