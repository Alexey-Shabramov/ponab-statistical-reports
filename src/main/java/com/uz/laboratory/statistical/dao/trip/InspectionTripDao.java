package com.uz.laboratory.statistical.dao.trip;


import com.uz.laboratory.statistical.dao.GenericDao;
import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.filter.RemarkStatisticsFilter;

import java.util.List;

public interface InspectionTripDao extends GenericDao {
    List getInspectionTripByFilter(RemarkStatisticsFilter remarkStatisticsFilter);

    List getInspectionTripsBySector(Sector sector);
}
