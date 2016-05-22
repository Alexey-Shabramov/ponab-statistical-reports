package com.uz.laboratory.statistical.filter;


import com.uz.laboratory.statistical.entity.location.CommunicationDistance;
import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.location.Stage;
import com.uz.laboratory.statistical.entity.location.Station;

public class AlsDevicesFilter {
    private Sector sector;
    private Stage stage;
    private Station station;
    private Boolean stationalTrackCircuit;
    private Boolean even;
    private Double picket;
    private CommunicationDistance communicationDistance;

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
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

    public Boolean getStationalTrackCircuit() {
        return stationalTrackCircuit;
    }

    public void setStationalTrackCircuit(Boolean stationalTrackCircuit) {
        this.stationalTrackCircuit = stationalTrackCircuit;
    }

    public Boolean getEven() {
        return even;
    }

    public void setEven(Boolean even) {
        this.even = even;
    }

    public Double getPicket() {
        return picket;
    }

    public void setPicket(Double picket) {
        this.picket = picket;
    }

    public CommunicationDistance getCommunicationDistance() {
        return communicationDistance;
    }

    public void setCommunicationDistance(CommunicationDistance communicationDistance) {
        this.communicationDistance = communicationDistance;
    }
}
