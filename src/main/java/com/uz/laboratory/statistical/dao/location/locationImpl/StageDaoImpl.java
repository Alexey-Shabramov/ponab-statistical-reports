package com.uz.laboratory.statistical.dao.location.locationImpl;

import com.uz.laboratory.statistical.dao.daoImpl.GenericDaoImpl;
import com.uz.laboratory.statistical.dao.location.StageDao;
import com.uz.laboratory.statistical.entity.location.Stage;


public class StageDaoImpl extends GenericDaoImpl<Stage> implements StageDao {
    @Override
    public Class getEntityClass() {
        return Stage.class;
    }
}
