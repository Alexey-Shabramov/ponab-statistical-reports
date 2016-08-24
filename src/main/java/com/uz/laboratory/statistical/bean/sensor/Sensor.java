package com.uz.laboratory.statistical.bean.sensor;

import com.dalsemi.onewire.application.tag.TaggedDevice;


public class Sensor extends TaggedDevice {
    private String sensorName;
    private boolean sensorOn;
    private String sensorId;
    private Double temperatureValue;

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public boolean isSensorOn() {
        return sensorOn;
    }

    public void setSensorOn(boolean sensorOn) {
        this.sensorOn = sensorOn;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public Double getTemperatureValue() {
        return temperatureValue;
    }

    public void setTemperatureValue(Double temperatureValue) {
        this.temperatureValue = temperatureValue;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "sensorName='" + sensorName + '\'' +
                ", sensorOn=" + sensorOn +
                ", sensorId='" + sensorId + '\'' +
                ", temperatureValue=" + temperatureValue +
                '}';
    }
}
