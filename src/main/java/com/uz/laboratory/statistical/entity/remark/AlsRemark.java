package com.uz.laboratory.statistical.entity.remark;


import com.uz.laboratory.statistical.entity.als.TrackCircuit;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "als_remark")
@DynamicUpdate(value = true)
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id")),
        @AttributeOverride(name = "inspectionTrip", column = @Column(name = "id_inspection")),
        @AttributeOverride(name = "repeatable", column = @Column(name = "repeatable")),
        @AttributeOverride(name = "creationDate", column = @Column(name = "creation_date"))
})
public class AlsRemark extends AbstractRemark {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_track_circuit")
    private TrackCircuit trackCircuit;

    @Column(name = "even")
    private boolean even;

    @Column(name = "note")
    private String note;

    public TrackCircuit getTrackCircuit() {
        return trackCircuit;
    }

    public void setTrackCircuit(TrackCircuit trackCircuit) {
        this.trackCircuit = trackCircuit;
    }

    public boolean isEven() {
        return even;
    }

    public void setEven(boolean even) {
        this.even = even;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
