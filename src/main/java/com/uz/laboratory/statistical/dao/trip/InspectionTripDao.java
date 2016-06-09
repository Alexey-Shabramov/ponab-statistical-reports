package com.uz.laboratory.statistical.dao.trip;


import com.uz.laboratory.statistical.dao.GenericDao;
import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.filter.TripFilter;

import java.util.List;

public interface InspectionTripDao extends GenericDao {
    List getInspectionListByFilter(TripFilter tripFilter);

    List getInspectionTripsBySector(Sector sector);

    List listAllEndedInspectionTripList();
}
