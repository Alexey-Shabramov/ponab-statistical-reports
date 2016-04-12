package com.uz.laboratory.statistical.filter;


import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.location.Stage;
import com.uz.laboratory.statistical.entity.trip.VagonLaboratory;

import java.util.Date;

public class StatisticsFilter {
    private Sector sector;
    private Stage stage;
    private String deviceType;
    private VagonLaboratory vagonLaboratory;
    private String directionOfMovement;
    private Date date;

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

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public VagonLaboratory getVagonLaboratory() {
        return vagonLaboratory;
    }

    public void setVagonLaboratory(VagonLaboratory vagonLaboratory) {
        this.vagonLaboratory = vagonLaboratory;
    }

    public String getDirectionOfMovement() {
        return directionOfMovement;
    }

    public void setDirectionOfMovement(String directionOfMovement) {
        this.directionOfMovement = directionOfMovement;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "StatisticsFilter{" +
                "sector=" + sector +
                ", stage=" + stage +
                ", deviceType='" + deviceType + '\'' +
                ", vagonLaboratory=" + vagonLaboratory +
                ", directionOfMovement='" + directionOfMovement + '\'' +
                ", date=" + date +
                '}';
    }
}
