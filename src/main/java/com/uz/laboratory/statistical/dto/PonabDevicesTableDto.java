package com.uz.laboratory.statistical.dto;

import javafx.beans.property.SimpleStringProperty;


public class PonabDevicesTableDto {
    private final SimpleStringProperty deviceId;
    private final SimpleStringProperty sectorTitle;
    private final SimpleStringProperty stageTitle;
    private final SimpleStringProperty systemTitle;
    private final SimpleStringProperty option;
    private final SimpleStringProperty speachInformator;
    private final SimpleStringProperty directionOfMovement;

    public PonabDevicesTableDto(Long deviceId, String sectorTitle, String stageTitle, String systemTitle, String option, String speachInformator, String directionOfMovement) {
        this.deviceId = new SimpleStringProperty(deviceId.toString());
        this.sectorTitle = new SimpleStringProperty(sectorTitle);
        this.stageTitle = new SimpleStringProperty(stageTitle);
        this.systemTitle = new SimpleStringProperty(systemTitle);
        this.option = new SimpleStringProperty(option);
        this.speachInformator = new SimpleStringProperty(speachInformator);
        this.directionOfMovement = new SimpleStringProperty(directionOfMovement);
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

    public String getSectorTitle() {
        return sectorTitle.get();
    }

    public void setSectorTitle(String sectorTitle) {
        this.sectorTitle.set(sectorTitle);
    }

    public SimpleStringProperty sectorTitleProperty() {
        return sectorTitle;
    }

    public String getStageTitle() {
        return stageTitle.get();
    }

    public void setStageTitle(String stageTitle) {
        this.stageTitle.set(stageTitle);
    }

    public SimpleStringProperty stageTitleProperty() {
        return stageTitle;
    }

    public String getSystemTitle() {
        return systemTitle.get();
    }

    public void setSystemTitle(String systemTitle) {
        this.systemTitle.set(systemTitle);
    }

    public SimpleStringProperty systemTitleProperty() {
        return systemTitle;
    }

    public String getOption() {
        return option.get();
    }

    public void setOption(String option) {
        this.option.set(option);
    }

    public SimpleStringProperty optionProperty() {
        return option;
    }

    public String getSpeachInformator() {
        return speachInformator.get();
    }

    public void setSpeachInformator(String speachInformator) {
        this.speachInformator.set(speachInformator);
    }

    public SimpleStringProperty speachInformatorProperty() {
        return speachInformator;
    }

    public String getDirectionOfMovement() {
        return directionOfMovement.get();
    }

    public void setDirectionOfMovement(String directionOfMovement) {
        this.directionOfMovement.set(directionOfMovement);
    }

    public SimpleStringProperty directionOfMovementProperty() {
        return directionOfMovement;
    }
}
