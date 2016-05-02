package com.uz.laboratory.statistical.dto.als;


import com.uz.laboratory.statistical.entity.als.TrackCircuit;
import com.uz.laboratory.statistical.entity.trip.InspectionTrip;

import java.util.Date;

public class AlsRemarkDto {
    private Long id;
    private InspectionTrip inspectionTrip;
    private Date creationDate;
    private Boolean repeatable = false;
    private TrackCircuit trackCircuit;
    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InspectionTrip getInspectionTrip() {
        return inspectionTrip;
    }

    public void setInspectionTrip(InspectionTrip inspectionTrip) {
        this.inspectionTrip = inspectionTrip;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getRepeatable() {
        return repeatable;
    }

    public void setRepeatable(Boolean repeatable) {
        this.repeatable = repeatable;
    }

    public TrackCircuit getTrackCircuit() {
        return trackCircuit;
    }

    public void setTrackCircuit(TrackCircuit trackCircuit) {
        this.trackCircuit = trackCircuit;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
