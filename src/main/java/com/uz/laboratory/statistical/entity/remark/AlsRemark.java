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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_track_circuit")
    private TrackCircuit track_circuit_name;

    @Column(name = "even")
    private boolean even;

    @Column(name = "note")
    private String note;

    public TrackCircuit getTrack_circuit_name() {
        return track_circuit_name;
    }

    public void setTrack_circuit_name(TrackCircuit track_circuit_name) {
        this.track_circuit_name = track_circuit_name;
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
