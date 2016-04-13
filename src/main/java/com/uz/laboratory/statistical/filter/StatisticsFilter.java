package com.uz.laboratory.statistical.filter;


import com.uz.laboratory.statistical.entity.location.Sector;
import com.uz.laboratory.statistical.entity.location.Stage;
import com.uz.laboratory.statistical.entity.trip.VagonLaboratory;

public class StatisticsFilter {
    private Sector sector;
    private Stage stage;
    private int deviceType;
    private VagonLaboratory vagonLaboratory;
    private String directionOfMovement;
    private Integer dayNumber;
    private Integer yearNumber;
    private String month;

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

    public String getDirectionOfMovement() {
        return directionOfMovement;
    }

    public void setDirectionOfMovement(String directionOfMovement) {
        this.directionOfMovement = directionOfMovement;
    }

    public Integer getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(Integer dayNumber) {
        this.dayNumber = dayNumber;
    }

    public Integer getYearNumber() {
        return yearNumber;
    }

    public void setYearNumber(Integer yearNumber) {
        this.yearNumber = yearNumber;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return "StatisticsFilter{" +
                "sector=" + sector +
                ", stage=" + stage +
                ", deviceType=" + deviceType +
                ", vagonLaboratory=" + vagonLaboratory +
                ", directionOfMovement='" + directionOfMovement + '\'' +
                ", dayNumber=" + dayNumber +
                ", yearNumber=" + yearNumber +
                ", month='" + month + '\'' +
                '}';
    }
}
