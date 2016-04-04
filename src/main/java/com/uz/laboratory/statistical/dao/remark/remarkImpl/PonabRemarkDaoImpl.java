package com.uz.laboratory.statistical.dao.remark.remarkImpl;

import com.uz.laboratory.statistical.dao.daoImpl.GenericDaoImpl;
import com.uz.laboratory.statistical.dao.remark.PonabRemarkDao;
import com.uz.laboratory.statistical.entity.remark.PonabRemark;


public class PonabRemarkDaoImpl extends GenericDaoImpl<PonabRemark> implements PonabRemarkDao {
    @Override
    public Class getEntityClass() {
        return PonabRemark.class;
    }
}
