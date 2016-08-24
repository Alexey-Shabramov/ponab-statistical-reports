package com.uz.laboratory.statistical.bean.movement;

import com.uz.laboratory.statistical.bean.sensor.Sensor;
import jssc.SerialPort;

public class UnevenMovement {
    private volatile Sensor leftIronSensor;
    private volatile Sensor rightIronSensor;
    private volatile SerialPort unevenControlPort;

    public UnevenMovement() {
        leftIronSensor = new Sensor();
        rightIronSensor = new Sensor();
    }

    public Sensor getLeftIronSensor() {
        return leftIronSensor;
    }

    public void setLeftIronSensor(Sensor leftIronSensor) {
        this.leftIronSensor = leftIronSensor;
    }

    public Sensor getRightIronSensor() {
        return rightIronSensor;
    }

    public void setRightIronSensor(Sensor rightIronSensor) {
        this.rightIronSensor = rightIronSensor;
    }

    public SerialPort getUnevenControlPort() {
        return unevenControlPort;
    }

    public void setUnevenControlPort(SerialPort unevenControlPort) {
        this.unevenControlPort = unevenControlPort;
    }

    @Override
    public String toString() {
        return "UnevenMovement{" +
                "leftIronSensor=" + leftIronSensor +
                ", rightIronSensor=" + rightIronSensor +
                ", unevenControlPort=" + unevenControlPort +
                '}';
    }
}
