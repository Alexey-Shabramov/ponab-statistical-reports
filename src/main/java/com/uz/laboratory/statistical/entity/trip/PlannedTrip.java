package com.uz.laboratory.statistical.entity.trip;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "planned_trip")
@DynamicUpdate(value = true)
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id")),
        @AttributeOverride(name = "tripSector", column = @Column(name = "id_sector")),
        @AttributeOverride(name = "vagonLaboratory", column = @Column(name = "id_vagon_laboratory")),
        @AttributeOverride(name = "beginDate", column = @Column(name = "begin_date")),
        @AttributeOverride(name = "endDate", column = @Column(name = "end_date"))
})
public class PlannedTrip extends AbstractTrip {
}
