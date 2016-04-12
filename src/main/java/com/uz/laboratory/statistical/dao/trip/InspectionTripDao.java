package com.uz.laboratory.statistical.dao.trip;


import com.uz.laboratory.statistical.dao.GenericDao;
import com.uz.laboratory.statistical.entity.trip.InspectionTrip;
import com.uz.laboratory.statistical.filter.StatisticsFilter;

import java.util.List;

public interface InspectionTripDao extends GenericDao {
    List<InspectionTrip> getInspectionTripByFilter(StatisticsFilter statisticsFilter);
}
