package com.uz.laboratory.statistical.entity.trip;


import com.uz.laboratory.statistical.entity.Identifier;
import com.uz.laboratory.statistical.entity.location.Sector;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class AbstractTrip extends Identifier {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sector")
    private Sector tripSector;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vagon_laboratory")
    private VagonLaboratory vagonLaboratory;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "begin_date")
    private Date beginDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_date")
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

    public VagonLaboratory getVagonLaboratory() {
        return vagonLaboratory;
    }

    public void setVagonLaboratory(VagonLaboratory vagonLaboratory) {
        this.vagonLaboratory = vagonLaboratory;
    }
}
