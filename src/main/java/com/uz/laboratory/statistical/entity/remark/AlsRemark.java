package com.uz.laboratory.statistical.entity.remark;


import com.uz.laboratory.statistical.entity.location.Stage;
import com.uz.laboratory.statistical.entity.location.Station;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "als_remark")
@DynamicUpdate(value = true)
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id")),
        @AttributeOverride(name = "inspectionTrip", column = @Column(name = "id_inspection")),
        @AttributeOverride(name = "creationDate", column = @Column(name = "creation_date"))
})
public class AlsRemark extends AbstractRemark {
    @Column(name = "even")
    private boolean even;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_stage")
    private Stage stage;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_station")
    private Station station;

    @Column(name = "note")
    private String note;

    @Column(name = "track_circuit_name")
    private String trackCircuitName;

    @Column(name = "remark_type")
    private boolean remarkType;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
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

    public String getTrackCircuitName() {
        return trackCircuitName;
    }

    public void setTrackCircuitName(String trackCircuitName) {
        this.trackCircuitName = trackCircuitName;
    }

    public boolean isRemarkType() {
        return remarkType;
    }

    public void setRemarkType(boolean remarkType) {
        this.remarkType = remarkType;
    }
}
