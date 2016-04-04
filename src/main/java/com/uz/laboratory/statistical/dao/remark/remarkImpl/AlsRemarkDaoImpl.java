package com.uz.laboratory.statistical.dao.remark.remarkImpl;

import com.uz.laboratory.statistical.dao.daoImpl.GenericDaoImpl;
import com.uz.laboratory.statistical.dao.remark.AlsRemarkDao;
import com.uz.laboratory.statistical.entity.remark.AlsRemark;


public class AlsRemarkDaoImpl extends GenericDaoImpl<AlsRemark> implements AlsRemarkDao {
    @Override
    public Class getEntityClass() {
        return AlsRemark.class;
    }
}
