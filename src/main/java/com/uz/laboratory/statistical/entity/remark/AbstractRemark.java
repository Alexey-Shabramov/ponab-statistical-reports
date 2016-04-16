package com.uz.laboratory.statistical.entity.remark;


import com.uz.laboratory.statistical.entity.Identifier;
import com.uz.laboratory.statistical.entity.trip.InspectionTrip;

import javax.persistence.*;
import java.util.Date;


@MappedSuperclass
public abstract class AbstractRemark extends Identifier {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_inspection_trip")
    private InspectionTrip inspectionTrip;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "repeatable")
    private boolean repeatable = false;

    public boolean isRepeatable() {
        return repeatable;
    }

    public void setRepeatable(boolean repeatable) {
        this.repeatable = repeatable;
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
}
