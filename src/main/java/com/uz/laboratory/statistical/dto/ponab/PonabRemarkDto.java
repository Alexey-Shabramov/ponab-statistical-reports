package com.uz.laboratory.statistical.dto.ponab;


import com.uz.laboratory.statistical.entity.ponab.PonabSystem;
import com.uz.laboratory.statistical.entity.trip.InspectionTrip;

import java.util.Date;

public class PonabRemarkDto {
    private Long id;
    private InspectionTrip inspectionTrip;
    private Date creationDate;
    private Boolean repeatable = false;
    private PonabSystem ponabSystem;
    private Boolean even;
    private String note;

    public PonabRemarkDto() {
    }

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

    public PonabSystem getPonabSystem() {
        return ponabSystem;
    }

    public void setPonabSystem(PonabSystem ponabSystem) {
        this.ponabSystem = ponabSystem;
    }

    public Boolean getEven() {
        return even;
    }

    public void setEven(Boolean even) {
        this.even = even;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "PonabRemarkDto{" +
                "id=" + id +
                ", inspectionTrip=" + inspectionTrip +
                ", creationDate=" + creationDate +
                ", repeatable=" + repeatable +
                ", ponabSystem=" + ponabSystem +
                ", even=" + even +
                ", note='" + note + '\'' +
                '}';
    }
}
