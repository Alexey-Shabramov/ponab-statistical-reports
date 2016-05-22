package com.uz.laboratory.statistical.dto.als;


import com.uz.laboratory.statistical.entity.location.CommunicationDistance;
import com.uz.laboratory.statistical.entity.location.Stage;
import com.uz.laboratory.statistical.entity.location.Station;

public class AlsSystemDto {
    private Long id;
    private Stage stage;
    private Station station;
    private boolean stationalCircuit;
    private boolean even;
    private Double picket;
    private String name;
    private CommunicationDistance communicationDistance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public CommunicationDistance getCommunicationDistance() {
        return communicationDistance;
    }

    public void setCommunicationDistance(CommunicationDistance communicationDistance) {
        this.communicationDistance = communicationDistance;
    }

    @Override
    public String toString() {
        return "AlsSystemDto{" +
                "id=" + id +
                ", stage=" + stage +
                ", station=" + station +
                ", stationalCircuit=" + stationalCircuit +
                ", even=" + even +
                ", picket=" + picket +
                ", name='" + name + '\'' +
                ", communicationDistance=" + communicationDistance +
                '}';
    }
}
