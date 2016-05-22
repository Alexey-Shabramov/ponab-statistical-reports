package com.uz.laboratory.statistical.dict;


public enum TrackCircuitTypes {
    STAGE("Перегонная"),
    STATION("Станционная");

    private String title;

    TrackCircuitTypes(String title) {
        this.title = title;
    }

    public String toString() {
        return title;
    }
}
