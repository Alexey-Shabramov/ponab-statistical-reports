package com.uz.laboratory.statistical.entity.location;

import com.uz.laboratory.statistical.entity.Identifier;

import java.util.List;

public class Stage extends Identifier {
    private String name;
    private Station firstStation;
    private Station secondStation;
    private List<CommunicationDistance> communicationDistanceList;

    public List<CommunicationDistance> getCommunicationDistanceList() {
        return communicationDistanceList;
    }

    public void setCommunicationDistanceList(List<CommunicationDistance> communicationDistanceList) {
        this.communicationDistanceList = communicationDistanceList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Station getFirstStation() {
        return firstStation;
    }

    public void setFirstStation(Station firstStation) {
        this.firstStation = firstStation;
    }

    public Station getSecondStation() {
        return secondStation;
    }

    public void setSecondStation(Station secondStation) {
        this.secondStation = secondStation;
    }
}
