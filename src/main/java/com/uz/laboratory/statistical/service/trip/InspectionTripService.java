package com.uz.laboratory.statistical.service.trip;

import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.service.GenericService;

import java.util.List;


public interface InspectionTripService extends GenericService {
    List getInspectionTripsBySector(Sector sector);
}
