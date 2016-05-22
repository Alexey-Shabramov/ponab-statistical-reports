package com.uz.laboratory.statistical.dto.tableView;

import javafx.beans.property.SimpleStringProperty;


public class AlsDevicesTableDto {
    private final SimpleStringProperty deviceId;
    private final SimpleStringProperty trackCircuitName;
    private final SimpleStringProperty sectorTitle;
    private final SimpleStringProperty stageOrStationTitle;
    private final SimpleStringProperty directionOfMovement;
    private final SimpleStringProperty trackCircuitType;
    private final SimpleStringProperty picketNumber;

    public AlsDevicesTableDto(String deviceId, String trackCircuitName, String sectorTitle, String stageOrStationTitle, String directionOfMovement, String trackCircuitType, String picketNumber) {
        this.deviceId = new SimpleStringProperty(deviceId);
        this.trackCircuitName = new SimpleStringProperty(trackCircuitName);
        this.sectorTitle = new SimpleStringProperty(sectorTitle);
        this.stageOrStationTitle = new SimpleStringProperty(stageOrStationTitle);
        this.directionOfMovement = new SimpleStringProperty(directionOfMovement);
        this.trackCircuitType = new SimpleStringProperty(trackCircuitType);
        this.picketNumber = new SimpleStringProperty(picketNumber);
    }

    public String getPicketNumber() {
        return picketNumber.get();
    }

    public void setPicketNumber(String picketNumber) {
        this.picketNumber.set(picketNumber);
    }

    public SimpleStringProperty picketNumberProperty() {
        return picketNumber;
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

    public String getTrackCircuitName() {
        return trackCircuitName.get();
    }

    public void setTrackCircuitName(String trackCircuitName) {
        this.trackCircuitName.set(trackCircuitName);
    }

    public SimpleStringProperty trackCircuitNameProperty() {
        return trackCircuitName;
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

    public String getStageOrStationTitle() {
        return stageOrStationTitle.get();
    }

    public void setStageOrStationTitle(String stageOrStationTitle) {
        this.stageOrStationTitle.set(stageOrStationTitle);
    }

    public SimpleStringProperty stageOrStationTitleProperty() {
        return stageOrStationTitle;
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

    public String getTrackCircuitType() {
        return trackCircuitType.get();
    }

    public void setTrackCircuitType(String trackCircuitType) {
        this.trackCircuitType.set(trackCircuitType);
    }

    public SimpleStringProperty trackCircuitTypeProperty() {
        return trackCircuitType;
    }
}
