package com.uz.laboratory.statistical.filter;


import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.trip.VagonLaboratory;

import java.util.Date;

public class TripFilter {
    private Sector tripSector;
    private VagonLaboratory vagonLaboratory;
    private Date beginDate;
    private Date endDate;
    private Boolean plannedTrip;

    public Boolean getPlannedTrip() {
        return plannedTrip;
    }

    public void setPlannedTrip(Boolean plannedTrip) {
        this.plannedTrip = plannedTrip;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Sector getTripSector() {
        return tripSector;
    }

    public void setTripSector(Sector tripSector) {
        this.tripSector = tripSector;
    }

    public VagonLaboratory getVagonLaboratory() {
        return vagonLaboratory;
    }

    public void setVagonLaboratory(VagonLaboratory vagonLaboratory) {
        this.vagonLaboratory = vagonLaboratory;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }
}
