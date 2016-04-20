package com.uz.laboratory.statistical.entity.als;

import com.uz.laboratory.statistical.entity.Identifier;
import com.uz.laboratory.statistical.entity.location.CommunicationDistance;
import com.uz.laboratory.statistical.entity.location.Stage;
import com.uz.laboratory.statistical.entity.location.Station;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


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

    @Column(name = "picket")
    private Double picket;

    @Column(name = "name")
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_communication_distance")
    private CommunicationDistance communicationDistance;

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
