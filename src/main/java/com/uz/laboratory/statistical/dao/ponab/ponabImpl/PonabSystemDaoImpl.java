package com.uz.laboratory.statistical.dao.ponab.ponabImpl;

import com.uz.laboratory.statistical.dao.daoImpl.GenericDaoImpl;
import com.uz.laboratory.statistical.dao.ponab.PonabSystemDao;
import com.uz.laboratory.statistical.entity.ponab.PonabSystem;


public class PonabSystemDaoImpl extends GenericDaoImpl<PonabSystem> implements PonabSystemDao {
    @Override
    public Class getEntityClass() {
        return PonabSystem.class;
    }
}
