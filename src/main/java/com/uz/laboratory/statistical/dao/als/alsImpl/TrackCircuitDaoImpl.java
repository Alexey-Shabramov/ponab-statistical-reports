package com.uz.laboratory.statistical.dao.als.alsImpl;

import com.uz.laboratory.statistical.dao.als.TrackCircuitDao;
import com.uz.laboratory.statistical.dao.daoImpl.GenericDaoImpl;
import com.uz.laboratory.statistical.entity.als.TrackCircuit;


public class TrackCircuitDaoImpl extends GenericDaoImpl<TrackCircuit> implements TrackCircuitDao {
    @Override
    public Class getEntityClass() {
        return TrackCircuit.class;
    }
}
