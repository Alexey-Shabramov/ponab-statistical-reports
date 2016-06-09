package com.uz.laboratory.statistical.dao.location.locationImpl;

import com.uz.laboratory.statistical.dao.daoImpl.GenericDaoImpl;
import com.uz.laboratory.statistical.dao.location.StationDao;
import com.uz.laboratory.statistical.entity.location.Station;


public class StationDaoImpl extends GenericDaoImpl<Station> implements StationDao {
    @Override
    public Class getEntityClass() {
        return Station.class;
    }

}
