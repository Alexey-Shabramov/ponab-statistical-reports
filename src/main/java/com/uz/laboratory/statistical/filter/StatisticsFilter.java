package com.uz.laboratory.statistical.filter;


import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.location.Stage;
import com.uz.laboratory.statistical.entity.trip.VagonLaboratory;

public class StatisticsFilter {
    private Sector sector;
    private Stage stage;
    private int deviceType;
    private VagonLaboratory vagonLaboratory;
    private Boolean directionOfMovement;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

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

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public VagonLaboratory getVagonLaboratory() {
        return vagonLaboratory;
    }

    public void setVagonLaboratory(VagonLaboratory vagonLaboratory) {
        this.vagonLaboratory = vagonLaboratory;
    }

    public Boolean getDirectionOfMovement() {
        return directionOfMovement;
    }

    public void setDirectionOfMovement(Boolean directionOfMovement) {
        this.directionOfMovement = directionOfMovement;
    }

    @Override
    public String toString() {
        return "StatisticsFilter{" +
                "sector=" + sector +
                ", stage=" + stage +
                ", deviceType=" + deviceType +
                ", vagonLaboratory=" + vagonLaboratory +
                ", directionOfMovement=" + directionOfMovement +
                ", date='" + date + '\'' +
                '}';
    }
}
