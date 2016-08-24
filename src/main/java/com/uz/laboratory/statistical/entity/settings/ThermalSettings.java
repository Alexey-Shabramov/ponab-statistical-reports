package com.uz.laboratory.statistical.entity.settings;

import com.uz.laboratory.statistical.entity.Identifier;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "termal_settings")
@DynamicUpdate(value = true)
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class ThermalSettings extends Identifier {
    @Column(name = "adapter_address")
    private String adapterAddress;

    @Column(name = "adapter_port")
    private String adapterPort;

    @Column(name = "even_right_iron_sensor_address")
    private String evenRightIronSensorAddress;

    @Column(name = "even_left_iron_sensor_address")
    private String evenLeftIronSensorAddress;

    @Column(name = "even_control_port_address")
    private String evenControlPortAddress;

    @Column(name = "uneven_right_iron_sensor_address")
    private String unevenRightIronSensorAddress;

    @Column(name = "uneven_left_iron_sensor_address")
    private String unevenLeftIronSensorAddress;

    @Column(name = "uneven_control_port_address")
    private String unevenControlPortAddress;

    @Column(name = "temperature_left_sensor_address")
    private String temperatureLeftSensorAddress;

    @Column(name = "temperature_right_sensor_address")
    private String temperatureRightSensorAddress;

    public String getAdapterAddress() {
        return adapterAddress;
    }

    public void setAdapterAddress(String adapterAddress) {
        this.adapterAddress = adapterAddress;
    }

    public String getAdapterPort() {
        return adapterPort;
    }

    public void setAdapterPort(String adapterPort) {
        this.adapterPort = adapterPort;
    }

    public String getEvenRightIronSensorAddress() {
        return evenRightIronSensorAddress;
    }

    public void setEvenRightIronSensorAddress(String evenRightIronSensorAddress) {
        this.evenRightIronSensorAddress = evenRightIronSensorAddress;
    }

    public String getEvenLeftIronSensorAddress() {
        return evenLeftIronSensorAddress;
    }

    public void setEvenLeftIronSensorAddress(String evenLeftIronSensorAddress) {
        this.evenLeftIronSensorAddress = evenLeftIronSensorAddress;
    }

    public String getEvenControlPortAddress() {
        return evenControlPortAddress;
    }

    public void setEvenControlPortAddress(String evenControlPortAddress) {
        this.evenControlPortAddress = evenControlPortAddress;
    }

    public String getUnevenRightIronSensorAddress() {
        return unevenRightIronSensorAddress;
    }

    public void setUnevenRightIronSensorAddress(String unevenRightIronSensorAddress) {
        this.unevenRightIronSensorAddress = unevenRightIronSensorAddress;
    }

    public String getUnevenLeftIronSensorAddress() {
        return unevenLeftIronSensorAddress;
    }

    public void setUnevenLeftIronSensorAddress(String unevenLeftIronSensorAddress) {
        this.unevenLeftIronSensorAddress = unevenLeftIronSensorAddress;
    }

    public String getUnevenControlPortAddress() {
        return unevenControlPortAddress;
    }

    public void setUnevenControlPortAddress(String unevenControlPortAddress) {
        this.unevenControlPortAddress = unevenControlPortAddress;
    }

    public String getTemperatureLeftSensorAddress() {
        return temperatureLeftSensorAddress;
    }

    public void setTemperatureLeftSensorAddress(String temperatureLeftSensorAddress) {
        this.temperatureLeftSensorAddress = temperatureLeftSensorAddress;
    }

    public String getTemperatureRightSensorAddress() {
        return temperatureRightSensorAddress;
    }

    public void setTemperatureRightSensorAddress(String temperatureRightSensorAddress) {
        this.temperatureRightSensorAddress = temperatureRightSensorAddress;
    }

    @Override
    public String toString() {
        return "ThermalSettings{" +
                "id='" + getId() + '\'' +
                "adapterAddress='" + adapterAddress + '\'' +
                ", adapterPort='" + adapterPort + '\'' +
                ", evenRightIronSensorAddress='" + evenRightIronSensorAddress + '\'' +
                ", evenLeftIronSensorAddress='" + evenLeftIronSensorAddress + '\'' +
                ", evenControlPortAddress='" + evenControlPortAddress + '\'' +
                ", unevenRightIronSensorAddress='" + unevenRightIronSensorAddress + '\'' +
                ", unevenLeftIronSensorAddress='" + unevenLeftIronSensorAddress + '\'' +
                ", unevenControlPortAddress='" + unevenControlPortAddress + '\'' +
                ", temperatureLeftSensorAddress='" + temperatureLeftSensorAddress + '\'' +
                ", temperatureRightSensorAddress='" + temperatureRightSensorAddress + '\'' +
                '}';
    }
}
