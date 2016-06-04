package com.uz.laboratory.statistical.dto.tableView;

import javafx.beans.property.SimpleStringProperty;


public class TripsTableDto {
    private final SimpleStringProperty deviceId;
    private final SimpleStringProperty sectorTitle;
    private final SimpleStringProperty vagonLaboratoryTitle;
    private final SimpleStringProperty date;

    public TripsTableDto(String deviceId, String sectorTitle, String vagonLaboratoryTitle, String date) {
        this.deviceId = new SimpleStringProperty(deviceId);
        this.sectorTitle = new SimpleStringProperty(sectorTitle);
        this.vagonLaboratoryTitle = new SimpleStringProperty(vagonLaboratoryTitle);
        this.date = new SimpleStringProperty(date);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public String getVagonLaboratoryTitle() {
        return vagonLaboratoryTitle.get();
    }

    public void setVagonLaboratoryTitle(String vagonLaboratoryTitle) {
        this.vagonLaboratoryTitle.set(vagonLaboratoryTitle);
    }

    public SimpleStringProperty vagonLaboratoryTitleProperty() {
        return vagonLaboratoryTitle;
    }

    public String getSectorTitle() {
        return sectorTitle.get();
    }

    public void setSectorTitle(String sectorTitle) {
        this.sectorTitle.set(sectorTitle);
    }

    public SimpleStringProperty sectorTitleProperty() {
        return sectorTitle;
    }

    public String getDeviceId() {
        return deviceId.get();
    }

    public void setDeviceId(String deviceId) {
        this.deviceId.set(deviceId);
    }

    public SimpleStringProperty deviceIdProperty() {
        return deviceId;
    }
}
