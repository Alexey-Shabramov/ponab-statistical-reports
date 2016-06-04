package com.uz.laboratory.statistical.dict;


public enum SheduleTripsTypes {
    PLANNED("Графиковая (запланированная)"),
    INSPECTION("Проведенная");

    private String title;

    SheduleTripsTypes(String title) {
        this.title = title;
    }

    public String toString() {
        return title;
    }
}
