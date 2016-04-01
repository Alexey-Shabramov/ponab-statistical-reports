package com.uz.laboratory.statistical.entity.trip;


import com.uz.laboratory.statistical.entity.Identifier;
import com.uz.laboratory.statistical.entity.location.Sector;

import java.util.Date;

public abstract class AbstractTrip extends Identifier {
    private Sector tripSector;
    private String vagonLaboratory;
    private Date beginDate;
    private Date endDate;

    public Sector getTripSector() {
        return tripSector;
    }

    public void setTripSector(Sector tripSector) {
        this.tripSector = tripSector;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getVagonLaboratory() {
        return vagonLaboratory;
    }

    public void setVagonLaboratory(String vagonLaboratory) {
        this.vagonLaboratory = vagonLaboratory;
    }
}
