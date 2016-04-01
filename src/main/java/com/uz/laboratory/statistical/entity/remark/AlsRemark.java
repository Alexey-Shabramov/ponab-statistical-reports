package com.uz.laboratory.statistical.entity.remark;


import com.uz.laboratory.statistical.entity.location.Stage;
import com.uz.laboratory.statistical.entity.location.Station;

public class AlsRemark extends AbstractRemark {
    private boolean even;
    private Stage stage;
    private Station station;
    private String note;
    private String trackCircuitName;
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
