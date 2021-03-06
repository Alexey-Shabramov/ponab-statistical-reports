package com.uz.laboratory.statistical.service.trip.tripImpl;


import com.uz.laboratory.statistical.dao.trip.tripImpl.InspectionTripDaoImpl;
import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.trip.InspectionTrip;
import com.uz.laboratory.statistical.filter.TripFilter;
import com.uz.laboratory.statistical.service.serviceImpl.GenericServiceImpl;
import com.uz.laboratory.statistical.service.trip.InspectionTripService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class InspectionTripServiceImpl extends GenericServiceImpl<InspectionTrip, InspectionTripDaoImpl> implements InspectionTripService {
    @Override
    @Transactional
    public List getInspectionTripsBySector(Sector sector) {
        System.out.println(sector.toString());
        return dao.getInspectionTripsBySector(sector);
    }

    @Override
    @Transactional
    public List getInspectionListByFilter(TripFilter tripFilter) {
        return dao.getInspectionListByFilter(tripFilter);
    }

    @Override
    @Transactional
    public List listAllEndedInspectionTripList() {
        return dao.listAllEndedInspectionTripList();
    }


}
