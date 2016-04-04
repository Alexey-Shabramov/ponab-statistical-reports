package com.uz.laboratory.statistical.dao.location.locationImpl;

import com.uz.laboratory.statistical.dao.daoImpl.GenericDaoImpl;
import com.uz.laboratory.statistical.dao.location.SectorDao;
import com.uz.laboratory.statistical.entity.location.Sector;


public class SectorDaoImpl extends GenericDaoImpl<Sector> implements SectorDao {
    @Override
    public Class getEntityClass() {
        return Sector.class;
    }
}
