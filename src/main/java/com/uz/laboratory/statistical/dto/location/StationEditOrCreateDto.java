package com.uz.laboratory.statistical.dto.location;


import com.uz.laboratory.statistical.entity.location.Station;

public class StationEditOrCreateDto {
    private Station station;

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }
}
