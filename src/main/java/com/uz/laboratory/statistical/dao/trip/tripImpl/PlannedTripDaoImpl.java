package com.uz.laboratory.statistical.dao.trip.tripImpl;


import com.uz.laboratory.statistical.dao.daoImpl.GenericDaoImpl;
import com.uz.laboratory.statistical.dao.trip.PlannedTripDao;
import com.uz.laboratory.statistical.entity.trip.PlannedTrip;

public class PlannedTripDaoImpl extends GenericDaoImpl<PlannedTrip> implements PlannedTripDao {
    @Override
    public Class getEntityClass() {
        return PlannedTrip.class;
    }
}
