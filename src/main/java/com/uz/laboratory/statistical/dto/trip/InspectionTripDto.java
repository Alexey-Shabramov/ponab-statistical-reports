package com.uz.laboratory.statistical.dto.trip;

import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.trip.VagonLaboratory;

import java.util.Date;


public class InspectionTripDto {
    private Long id;
    private Sector tripSector;
    private VagonLaboratory vagonLaboratory;
    private Date date;
    private Boolean plannedTrip;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getPlannedTrip() {
        return plannedTrip;
    }

    public void setPlannedTrip(Boolean plannedTrip) {
        this.plannedTrip = plannedTrip;
    }
}
