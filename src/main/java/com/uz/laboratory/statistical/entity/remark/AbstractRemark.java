package com.uz.laboratory.statistical.entity.remark;


import com.uz.laboratory.statistical.entity.Identifier;
import com.uz.laboratory.statistical.entity.trip.Inspection;

import java.util.Date;


public abstract class AbstractRemark extends Identifier {
    private Inspection inspectionTrip;
    private Date remarkDate;

    public Inspection getInspectionTrip() {
        return inspectionTrip;
    }

    public void setInspectionTrip(Inspection inspectionTrip) {
        this.inspectionTrip = inspectionTrip;
    }

    public Date getRemarkDate() {
        return remarkDate;
    }

    public void setRemarkDate(Date remarkDate) {
        this.remarkDate = remarkDate;
    }
}
