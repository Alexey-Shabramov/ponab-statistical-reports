package com.uz.laboratory.statistical.entity.als;

import com.uz.laboratory.statistical.entity.Identifier;
import com.uz.laboratory.statistical.entity.location.CommunicationDistance;
import com.uz.laboratory.statistical.entity.location.Stage;
import com.uz.laboratory.statistical.entity.location.Station;
import com.uz.laboratory.statistical.entity.remark.AlsRemark;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "track_circuit")
@DynamicUpdate(value = true)
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class TrackCircuit extends Identifier {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_stage")
    private Stage stage;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_station")
    private Station station;

    @Column(name = "stational_circuit")
    private boolean stationalCircuit;

    @Column(name = "even")
    private boolean even;

    @Column(name = "picket")
    private Double picket;

    @Column(name = "name")
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_communication_distance")
    private CommunicationDistance communicationDistance;

    @OneToMany(mappedBy = "trackCircuit", cascade = CascadeType.REMOVE)
    private List<AlsRemark> alsRemarks;

    public List<AlsRemark> getAlsRemarks() {
        return alsRemarks;
    }

    public void setAlsRemarks(List<AlsRemark> alsRemarks) {
        this.alsRemarks = alsRemarks;
    }

    public boolean isStationalCircuit() {
        return stationalCircuit;
    }

    public void setStationalCircuit(boolean stationalCircuit) {
        this.stationalCircuit = stationalCircuit;
    }

    public boolean isEven() {
        return even;
    }

    public void setEven(boolean even) {
        this.even = even;
    }

    public Double getPicket() {
        return picket;
    }

    public void setPicket(Double picket) {
        this.picket = picket;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public CommunicationDistance getCommunicationDistance() {
        return communicationDistance;
    }

    public void setCommunicationDistance(CommunicationDistance communicationDistance) {
        this.communicationDistance = communicationDistance;
    }
}
