package com.uz.laboratory.statistical.dao.trip.tripImpl;

import com.uz.laboratory.statistical.dao.daoImpl.GenericDaoImpl;
import com.uz.laboratory.statistical.dao.trip.InspectionTripDao;
import com.uz.laboratory.statistical.entity.trip.InspectionTrip;


public class InspectionTripDaoImpl extends GenericDaoImpl<InspectionTrip> implements InspectionTripDao {
    @Override
    public Class getEntityClass() {
        return InspectionTrip.class;
    }
}
