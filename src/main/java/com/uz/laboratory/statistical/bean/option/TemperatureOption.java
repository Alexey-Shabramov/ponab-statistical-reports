package com.uz.laboratory.statistical.bean.option;


import jssc.SerialPort;

import java.util.Date;

public class TemperatureOption {
    private boolean warmingOn = false;
    private boolean evenDirectionOfMovement;

    private Double outerTemperature;

    private SerialPort controlPortAddressByMovment;

    private Double installLeftTemperature;
    private Double installRightTemperature;

    private Double leftTemperature;
    private Double rightTemperature;

    private String systemOption;

    private Date date = new Date();

    public TemperatureOption() {
    }

    public Double getOuterTemperature() {
        return outerTemperature;
    }

    public void setOuterTemperature(Double outerTemperature) {
        this.outerTemperature = outerTemperature;
    }

    public Date getDate() {
        return new Date(date.getTime());
    }

    public void setDate(Date date) {
        this.date = new Date(date.getTime());
    }

    public Double getInstallLeftTemperature() {
        return installLeftTemperature;
    }

    public void setInstallLeftTemperature(Double installLeftTemperature) {
        this.installLeftTemperature = installLeftTemperature;
    }

    public Double getInstallRightTemperature() {
        return installRightTemperature;
    }

    public void setInstallRightTemperature(Double installRightTemperature) {
        this.installRightTemperature = installRightTemperature;
    }

    public boolean isWarmingOn() {
        return warmingOn;
    }

    public void setWarmingOn(boolean warmingOn) {
        this.warmingOn = warmingOn;
    }

    public boolean isEvenDirectionOfMovement() {
        return evenDirectionOfMovement;
    }

    public void setEvenDirectionOfMovement(boolean evenDirectionOfMovement) {
        this.evenDirectionOfMovement = evenDirectionOfMovement;
    }

    public SerialPort getControlPortAddressByMovment() {
        return controlPortAddressByMovment;
    }

    public void setControlPortAddressByMovment(SerialPort controlPortAddressByMovment) {
        this.controlPortAddressByMovment = controlPortAddressByMovment;
    }

    public Double getLeftTemperature() {
        return leftTemperature;
    }

    public void setLeftTemperature(Double leftTemperature) {
        this.leftTemperature = leftTemperature;
    }

    public Double getRightTemperature() {
        return rightTemperature;
    }

    public void setRightTemperature(Double rightTemperature) {
        this.rightTemperature = rightTemperature;
    }

    public String getSystemOption() {
        return systemOption;
    }

    public void setSystemOption(String systemOption) {
        this.systemOption = systemOption;
    }
}
